package com.eservices.waray.myuniversitycampus;

import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.eservices.waray.myuniversitycampus.entity.Problem;
import com.eservices.waray.myuniversitycampus.model.ProblemViewModel;
import com.eservices.waray.myuniversitycampus.ui.ProblemsListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemFragment.OnListFragmentInteractionListener {

    private RecyclerView recyclerView;
    private ProblemsListAdapter adapter;
//    private ProblemViewModel problemViewModel;
    private RecyclerView.LayoutManager layoutManager;
    private ItemFragment itemFragment;
    private MyItemRecyclerViewAdapter mAdapter;
    private List<Problem> mValues;
    private Bundle args;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        problemViewModel = ViewModelProviders.of(this).get(ProblemViewModel.class);


        itemFragment = new ItemFragment();


        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, itemFragment).commit();

//        recyclerView = findViewById(R.id.recyclerview);
//
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        adapter = new ProblemsListAdapter(this);
//        recyclerView.setAdapter(adapter);
//        problemViewModel = ViewModelProviders.of(this).get(ProblemViewModel.class);
//        problemViewModel.getAllProblems().observe(this, new Observer<List<Problem>>() {
//            @Override
//            public void onChanged(@Nullable List<Problem> problems) {
//                //update the cached problems in adapter
//                adapter.setAllProblems(problems);
//            }
//        });
//
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttachFragment(Fragment fragment){
        if (fragment instanceof ItemFragment) {
            ItemFragment itemFragment = (ItemFragment) fragment;
            itemFragment.setOnListFragmentInteractionListener(this);
        }
    }

    @Override
    public void onItemClick(Problem item) {

    }

    public void waa(){
        
    }
}
