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

/**
 * Created by LateRoad on 12.03.2018.
 */

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripsAdapterViewHolder> {
    private List<Order> mTripsData;

    public TripsAdapter() {

    }

    public class TripsAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTripDateTextView;
        public final TextView mTripDriverView;
        public final TextView mTripMoneyView;

        public TripsAdapterViewHolder(View view) {
            super(view);
            mTripDateTextView = (TextView) view.findViewById(R.id.tv_trip_date);
            mTripDriverView = (TextView) view.findViewById(R.id.tv_trip_driver_login);
            mTripMoneyView = (TextView) view.findViewById(R.id.tv_trip_money);
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
        tripsAdapterViewHolder.mTripDateTextView.setText(trip.getDate().toString());
        tripsAdapterViewHolder.mTripDriverView.setText(trip.getDriverLogin());
        tripsAdapterViewHolder.mTripMoneyView.setText("BYN" + trip.getMoney());
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