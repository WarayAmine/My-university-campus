package com.eservices.waray.myuniversitycampus;

import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eservices.waray.myuniversitycampus.entity.Problem;
import com.eservices.waray.myuniversitycampus.viewmodel.ProblemViewModel;

import java.text.SimpleDateFormat;

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

    /**
     * The dummy content this fragment is presenting.
     */
//    private DummyContent.DummyItem mItem;
    private Problem mItem;

    private ProblemViewModel problemViewModel;

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
                appBarLayout.setTitle(mItem.getType().toString());
            }

        }

        problemViewModel = ViewModelProviders.of(this).get(ProblemViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.problem_detail, container, false);

        // Show the problem content as text in a layout
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.pDescription)).setText(mItem.getDescription());
            ((TextView) rootView.findViewById(R.id.pDate)).setText(getText(R.string.porblem_spotted) + " " + new SimpleDateFormat("dd-MM-yyyy").format(mItem.getDate()));
            ((TextView) rootView.findViewById(R.id.pAddress)).setText("À " + mItem.getAddress());
            ((Button) rootView.findViewById(R.id.buttonDelete)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(R.string.delete_confirmation)
                            .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deleteProblem(mItem.getId());
                                    getActivity().onBackPressed();
                                }
                            })
                            .setNegativeButton(R.string.delete_cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create().show();
                }
            });;
        }

        return rootView;
    }

    public void deleteProblem(int id){
        problemViewModel.deleteProblemById(id);
    }

}
