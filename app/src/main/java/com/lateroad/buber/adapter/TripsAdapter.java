package com.lateroad.buber.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lateroad.buber.R;

import java.util.List;

/**
 * Created by LateRoad on 12.03.2018.
 */

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripsAdapterViewHolder> {
    private List mTripsData;

    public TripsAdapter() {

    }

    public class TripsAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTripTextView;

        public TripsAdapterViewHolder(View view) {
            super(view);
            mTripTextView = (TextView) view.findViewById(R.id.tv_trip_data);
        }
    }

    @Override
    public TripsAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.trip_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new TripsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TripsAdapterViewHolder tripsAdapterViewHolder, int position) {
        String weatherForThisDay = mTripsData.get(position).toString();
        tripsAdapterViewHolder.mTripTextView.setText(weatherForThisDay);
    }

    @Override
    public int getItemCount() {
        if (null == mTripsData) return 0;
        return mTripsData.size();
    }

    public void setData(List orders) {
        mTripsData = orders;
        notifyDataSetChanged();
    }
}