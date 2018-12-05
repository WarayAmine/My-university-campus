package com.eservices.waray.myuniversitycampus.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eservices.waray.myuniversitycampus.ProblemDetailActivity;
import com.eservices.waray.myuniversitycampus.ProblemDetailFragment;
import com.eservices.waray.myuniversitycampus.ProblemListActivity;
import com.eservices.waray.myuniversitycampus.R;
import com.eservices.waray.myuniversitycampus.entity.Problem;

import java.text.SimpleDateFormat;
import java.util.List;

public class ProblemsRecyclerViewAdapter extends RecyclerView.Adapter<ProblemsRecyclerViewAdapter.ViewHolder> {

    private final ProblemListActivity mParentActivity;
    private List<Problem> mValues;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
            Problem item = (Problem) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
//                    arguments.putString(ProblemDetailFragment.ARG_ITEM_ID, item.id);
                arguments.putSerializable(ProblemDetailFragment.ARG_ITEM_ID, item);
                ProblemDetailFragment fragment = new ProblemDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.problem_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, ProblemDetailActivity.class);
                intent.putExtra(ProblemDetailFragment.ARG_ITEM_ID, item);

                context.startActivity(intent);
            }
        }
    };

    public ProblemsRecyclerViewAdapter(ProblemListActivity parent,
                                       List<Problem> items,
                                       boolean twoPane) {
        mValues = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    public void setAllProblems(List<Problem> problems){
        mValues = problems;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.problem_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).getId().toString());
        holder.mDescription.setText(mValues.get(position).getDescription());
        holder.mAddress.setText(mValues.get(position).getAddress());
        holder.mDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(mValues.get(position).getDate()));
        holder.mType.setText(mValues.get(position).getType().toString());
        String imgResource = "type"+mValues.get(position).getType().getTypeValue()+mValues.get(position).getType().getTypeValue();
        int imgID;
        try {
            imgID = R.mipmap.class.getField(imgResource).getInt(null);
            holder.imageView.setImageResource(imgID);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        if(mValues != null)
            return mValues.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mDescription;
        final TextView mDate;
        final TextView mAddress;
        final TextView mType;
        final ImageView imageView;

        ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id_text);
            mDescription = (TextView) view.findViewById(R.id.description);
            mAddress = (TextView) view.findViewById(R.id.textViewAddress);
            mDate = (TextView) view.findViewById(R.id.textViewDate);
            mType = (TextView) view.findViewById(R.id.textViewType);
            imageView = (ImageView) view.findViewById(R.id.imageViewType);
        }
    }
}

