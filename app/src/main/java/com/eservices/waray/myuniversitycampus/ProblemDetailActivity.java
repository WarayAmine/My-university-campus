package com.eservices.waray.myuniversitycampus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.eservices.waray.myuniversitycampus.entity.Problem;

import static com.eservices.waray.myuniversitycampus.ProblemDetailFragment.ARG_ITEM_ID;

/**
 * An activity representing a single Problem detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ProblemListActivity}.
 */
public class ProblemDetailActivity extends AppCompatActivity {

    Problem problem;
    ImageView imageViewDetailType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProblemOnMap();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        imageViewDetailType = findViewById(R.id.imageViewDetailsType);

        if(getIntent().getSerializableExtra(ARG_ITEM_ID)!=null){
            problem = (Problem) getIntent().getSerializableExtra(ARG_ITEM_ID);
        }

        String imgResource = "typewhite"+problem.getType().getTypeValue();
        int imgID;
        try {
            imgID = R.mipmap.class.getField(imgResource).getInt(null);
            imageViewDetailType.setImageResource(imgID);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putSerializable(ARG_ITEM_ID,
                    getIntent().getSerializableExtra(ARG_ITEM_ID));

            ProblemDetailFragment fragment = new ProblemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.problem_detail_container, fragment)
                    .commit();
        }

    }

    // Using the google maps intent to show the problem's location
    private void showProblemOnMap() {
        if(getIntent().getSerializableExtra(ARG_ITEM_ID)!=null){
            Problem problem = (Problem) getIntent().getSerializableExtra(ARG_ITEM_ID);

            Uri mapUri = Uri.parse("geo:0,0?q="+problem.getLat()+","+problem.getLng()+"("+problem.getType().toString()+")");
            Intent intent = new Intent(Intent.ACTION_VIEW, mapUri);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, ProblemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
