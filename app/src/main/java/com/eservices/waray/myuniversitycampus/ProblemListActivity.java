package com.eservices.waray.myuniversitycampus;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eservices.waray.myuniversitycampus.adapter.ProblemsRecyclerViewAdapter;
import com.eservices.waray.myuniversitycampus.dummy.DummyContent;
import com.eservices.waray.myuniversitycampus.entity.Problem;
import com.eservices.waray.myuniversitycampus.model.ProblemViewModel;

import java.util.List;

import static com.eservices.waray.myuniversitycampus.Constants.staticProblems;

/**
 * An activity representing a list of Problems. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ProblemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ProblemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private ProblemViewModel problemViewModel;
    private ProblemsRecyclerViewAdapter mAdapter;
    private Intent intentNewProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
                intentNewProblem = new Intent(getApplicationContext(),AddNewProblemActivity.class);
                startActivity(intentNewProblem);
            }
        });

        if (findViewById(R.id.problem_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        problemViewModel = ViewModelProviders.of(this).get(ProblemViewModel.class);
        mAdapter = new ProblemsRecyclerViewAdapter(this, problemViewModel.getAllProblems().getValue(), mTwoPane);
        problemViewModel.getAllProblems().observe(this, new Observer<List<Problem>>() {
            @Override
            public void onChanged(@Nullable List<Problem> allProblems) {
                //update the cached problems in adapter
//                problems = allProblems;
                mAdapter.setAllProblems(allProblems);
            }
        });


        View recyclerView = findViewById(R.id.problem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView, mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.deleteAll : {
                problemViewModel.deleteAllProblems();
                break;
            }
            case R.id.addAll : {
                for (Problem p : staticProblems){
                    problemViewModel.insertProblem(p);
                }
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, ProblemsRecyclerViewAdapter problemsRecyclerViewAdapter) {
        recyclerView.setAdapter(problemsRecyclerViewAdapter);
    }

}