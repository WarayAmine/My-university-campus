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
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eservices.waray.myuniversitycampus.adapter.ProblemsRecyclerViewAdapter;
import com.eservices.waray.myuniversitycampus.dummy.DummyContent;
import com.eservices.waray.myuniversitycampus.entity.Problem;
import com.eservices.waray.myuniversitycampus.model.ProblemViewModel;

import java.util.List;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
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

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, ProblemsRecyclerViewAdapter problemsRecyclerViewAdapter) {
        recyclerView.setAdapter(problemsRecyclerViewAdapter);
    }

}

//    public static class SimpleItemRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
//
//        private final ProblemListActivity mParentActivity;
//        private List<Problem> mValues;
//        private final boolean mTwoPane;
//        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
//                Problem item = (Problem) view.getTag();
//                if (mTwoPane) {
//                    Bundle arguments = new Bundle();
////                    arguments.putString(ProblemDetailFragment.ARG_ITEM_ID, item.id);
//                    arguments.putSerializable(ProblemDetailFragment.ARG_ITEM_ID, item);
//                    ProblemDetailFragment fragment = new ProblemDetailFragment();
//                    fragment.setArguments(arguments);
//                    mParentActivity.getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.problem_detail_container, fragment)
//                            .commit();
//                } else {
//                    Context context = view.getContext();
//                    Intent intent = new Intent(context, ProblemDetailActivity.class);
//                    intent.putExtra(ProblemDetailFragment.ARG_ITEM_ID, item);
//
//                    context.startActivity(intent);
//                }
//            }
//        };
//
//        SimpleItemRecyclerViewAdapter(ProblemListActivity parent,
//                                      List<Problem> items,
//                                      boolean twoPane) {
//            mValues = items;
//            mParentActivity = parent;
//            mTwoPane = twoPane;
//        }
//
//        public void setAllProblems(List<Problem> problems){
//            mValues = problems;
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.problem_list_content, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
//            holder.mIdView.setText(mValues.get(position).getId().toString());
//            holder.mContentView.setText(mValues.get(position).getDescription());
//
//            holder.itemView.setTag(mValues.get(position));
//            holder.itemView.setOnClickListener(mOnClickListener);
//        }
//
//        @Override
//        public int getItemCount() {
//            if(mValues != null)
//                return mValues.size();
//            else return 0;
//        }
//
//        class ViewHolder extends RecyclerView.ViewHolder {
//            final TextView mIdView;
//            final TextView mContentView;
//
//            ViewHolder(View view) {
//                super(view);
//                mIdView = (TextView) view.findViewById(R.id.id_text);
//                mContentView = (TextView) view.findViewById(R.id.content);
//            }
//        }
//    }
