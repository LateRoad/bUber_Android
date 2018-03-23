package com.lateroad.buber.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lateroad.buber.R;
import com.lateroad.buber.entity.Order;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LateRoad on 12.03.2018.
 */

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripsAdapterViewHolder> {
    private List<Order> mTripsData;

    public TripsAdapter() {

    }

    public class TripsAdapterViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTripTextView;
        private final TextView mTripDriverView;

        public TripsAdapterViewHolder(View view) {
            super(view);
            mTripTextView = (TextView) view.findViewById(R.id.tv_trip_data);
            mTripDriverView = (TextView) view.findViewById(R.id.tv_driver_login);
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
        Order trip = mTripsData.get(position);
        tripsAdapterViewHolder.mTripTextView.setText(trip.toString());
        tripsAdapterViewHolder.mTripDriverView.setText(trip.getDriverLogin());
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