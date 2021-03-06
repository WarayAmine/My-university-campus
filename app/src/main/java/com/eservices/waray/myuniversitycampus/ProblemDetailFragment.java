package com.eservices.waray.myuniversitycampus;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.eservices.waray.myuniversitycampus.dummy.DummyContent;
import com.eservices.waray.myuniversitycampus.entity.Problem;
import com.eservices.waray.myuniversitycampus.model.ProblemViewModel;

import java.util.List;

/**
 * A fragment representing a single Problem detail screen.
 * This fragment is either contained in a {@link ProblemListActivity}
 * in two-pane mode (on tablets) or a {@link ProblemDetailActivity}
 * on handsets.
 */
public class ProblemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private ProblemViewModel problemViewModel;

    /**
     * The dummy content this fragment is presenting.
     */
//    private DummyContent.DummyItem mItem;
    private Problem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProblemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            mItem = (Problem) getArguments().getSerializable(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getId().toString());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.problem_detail, container, false);

        // Show the problem content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.problem_detail)).setText(mItem.getDescription());
        }

        return rootView;
    }
}
