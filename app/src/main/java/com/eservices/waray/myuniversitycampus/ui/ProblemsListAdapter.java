package com.eservices.waray.myuniversitycampus.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.*;
import android.widget.TextView;
import android.widget.Toast;

import com.eservices.waray.myuniversitycampus.R;
import com.eservices.waray.myuniversitycampus.entity.Problem;

import java.util.List;

public class ProblemsListAdapter extends RecyclerView.Adapter<ProblemsListAdapter.ProblemViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<Problem> allProblems;

    public ProblemsListAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    public static class ProblemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView idItemView;

        public ProblemViewHolder(View itemView){
            super(itemView);
            idItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }

    }

    @NonNull
    @Override
    public ProblemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ProblemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProblemViewHolder holder, int position) {
        if(allProblems != null){
            Problem currentProblem = allProblems.get(position);
            holder.idItemView.setText(currentProblem.getId().toString());
        } else {
            holder.idItemView.setText("No problem yet !");
        }
    }

    public void setAllProblems(List<Problem> problems){
        allProblems = problems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(allProblems != null)
            return allProblems.size();
        else return 0;
    }


}
