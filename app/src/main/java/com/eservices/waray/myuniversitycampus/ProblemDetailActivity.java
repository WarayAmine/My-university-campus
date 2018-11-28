package com.eservices.waray.myuniversitycampus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.eservices.waray.myuniversitycampus.entity.Problem;

/**
 * An activity representing a single Problem detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ProblemListActivity}.
 */
public class ProblemDetailActivity extends AppCompatActivity {

    
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

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putSerializable(ProblemDetailFragment.ARG_ITEM_ID,
                    getIntent().getSerializableExtra(ProblemDetailFragment.ARG_ITEM_ID));
            ProblemDetailFragment fragment = new ProblemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.problem_detail_container, fragment)
                    .commit();
        }
    }

    private void showProblemOnMap() {
        if(getIntent().getSerializableExtra(ProblemDetailFragment.ARG_ITEM_ID)!=null){
            Problem problem = (Problem) getIntent().getSerializableExtra(ProblemDetailFragment.ARG_ITEM_ID);

            Uri mapUri = Uri.parse("geo:0,0?q="+problem.getLat()+","+problem.getLng()+"("+problem.getType().toString()+")");
            Intent intent = new Intent(Intent.ACTION_VIEW, mapUri);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, ProblemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
