package com.eservices.waray.myuniversitycampus.utils;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.eservices.waray.myuniversitycampus.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Geocoder
public class FetchAddressIntentService extends IntentService {

    private static final String TAG = "FetchAddressIS";

    protected ResultReceiver mReceiver;

    public FetchAddressIntentService() {
        super(TAG);
    }

    public FetchAddressIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String errorMessage = "";

        mReceiver = intent.getParcelableExtra(Constants.RECEIVER);

        if (mReceiver == null) {
            Log.wtf(TAG, "No receiver received. There is nowhere to send the results.");
            return;
        }

        double lat = intent.getDoubleExtra(Constants.LOCATION_LAT,0);
        double lng = intent.getDoubleExtra(Constants.LOCATION_LNG,0);

        if (lat == 0 || lng == 0) {
            errorMessage = getString(R.string.no_location_data_provided);
            Log.wtf(TAG, errorMessage);
            deliverResultToReceiver(Constants.FAILURE_RESULT, errorMessage);
            return;
        }

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat,lng,1);
        } catch (IOException e){
            errorMessage = getString(R.string.service_not_available);
            Log.e(TAG, errorMessage, e);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = getString(R.string.invalid_lat_long_used);
            Log.e(TAG, errorMessage + ". " +
                    "Latitude = " + lat +
                    ", Longitude = " + lng, illegalArgumentException);
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
                Log.e(TAG, errorMessage);
            }
            deliverResultToReceiver(Constants.FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<>();

            // Fetch the address lines using {@code getAddressLine},
            // join them, and send them to the thread. The {@link android.location.address}
            // class provides other options for fetching address details that you may prefer
            // to use. Here are some examples:
            // getLocality() ("Mountain View", for example)
            // getAdminArea() ("CA", for example)
            // getPostalCode() ("94043", for example)
            // getCountryCode() ("US", for example)
            // getCountryName() ("United States", for example)
            for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            Log.i(TAG, getString(R.string.address_found));
            deliverResultToReceiver(Constants.SUCCESS_RESULT,
                    TextUtils.join(System.getProperty("line.separator"), addressFragments));
        }
    }

    /**
     * Sends a resultCode and message to the receiver.
     */
    private void deliverResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_DATA_KEY, message);
        mReceiver.send(resultCode, bundle);
    }
}