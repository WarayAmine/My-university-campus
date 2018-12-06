package com.eservices.waray.myuniversitycampus;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eservices.waray.myuniversitycampus.entity.Problem;
import com.eservices.waray.myuniversitycampus.utils.Constants;
import com.eservices.waray.myuniversitycampus.viewmodel.ProblemViewModel;
import com.eservices.waray.myuniversitycampus.utils.FetchAddressIntentService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Date;

public class AddNewProblemActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQUEST_LOCATION = 0;

    private Spinner spinner;
    private Button button;
    private Button buttonGeocoder;
    private TextView textViewAddress;
    private TextView textViewDescription;
    private Problem.ProblemType problemType;
    private Problem problem;
    private ProblemViewModel problemViewModel;
    private Intent intent;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private double lat,lng;
    private String address;
    private View layout;
    protected Location location;
    private AddressResultReceiver mResultReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_problem);
        layout = findViewById(R.id.scrollViewAddProblem);
        problemViewModel = ViewModelProviders.of(this).get(ProblemViewModel.class);
        mResultReceiver = new AddressResultReceiver(new Handler());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
//        startIntentService();

        intent = new Intent(this,ProblemListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<Problem.ProblemType>(this, android.R.layout.simple_dropdown_item_1line, Problem.ProblemType.values()));
        spinner.setOnItemSelectedListener(this);

        textViewAddress = (TextView) findViewById(R.id.textViewAddress);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        button = (Button) findViewById(R.id.buttonCreate);

        buttonGeocoder = findViewById(R.id.buttonGeocoder);
        buttonGeocoder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntentService();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                problem = new Problem(textViewAddress.getText().toString(),textViewDescription.getText().toString(),lat,lng,problemType,false,new Date(),new Date());
                problemViewModel.insertProblem(problem);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        problemType = Problem.ProblemType.getProblemType(position);
        Toast.makeText(getApplicationContext(),problemType.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void getLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location loc) {
                            if (loc != null) {
                                location = loc;
                                lat = loc.getLatitude();
                                lng = loc.getLongitude();
                                Toast.makeText(getApplicationContext(),"Lat : "+location.getLatitude()+" Long : "+location.getLongitude(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            requestLocationPermission();
        }
    }

    private void requestLocationPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
            Snackbar.make(layout, R.string.location_access_required,
                    Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Request the permission here
                    ActivityCompat.requestPermissions(AddNewProblemActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            PERMISSION_REQUEST_LOCATION);
                }
            }).show();
        } else {
            Snackbar.make(layout,R.string.location_unavailable, Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            // Request for location permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted.
                getLocation();
            } else {
                // Permission request was denied.
                Snackbar.make(layout, R.string.location_access_denied,
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }

    /**
     * Creates an intent, adds location data to it as an extra, and starts the intent service for
     * fetching an address.
     */
    private void startIntentService() {
        // Create an intent for passing to the intent service responsible for fetching the address.
        Intent intent = new Intent(this, FetchAddressIntentService.class);

        // Pass the result receiver as an extra to the service.
        intent.putExtra(Constants.RECEIVER, mResultReceiver);

        // Pass the location data as an extra to the service.
//        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        intent.putExtra(Constants.LOCATION_LAT, lat);
        intent.putExtra(Constants.LOCATION_LNG, lng);

        // Start the service. If the service isn't already running, it is instantiated and started
        // (creating a process for it if needed); if it is running then it remains running. The
        // service kills itself automatically once all intents are processed.
        startService(intent);
    }

    /**
     * Receiver for data sent from FetchAddressIntentService.
     */
    private class AddressResultReceiver extends ResultReceiver {
        AddressResultReceiver(Handler handler) {
            super(handler);
        }

        /**
         *  Receives data sent from FetchAddressIntentService and updates the UI in MainActivity.
         */
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Display the address string or an error message sent from the intent service.
            address = resultData.getString(Constants.RESULT_DATA_KEY);
            Toast.makeText(getApplicationContext(),address,Toast.LENGTH_SHORT).show();
            textViewAddress.setText(address);

        }
    }

}
