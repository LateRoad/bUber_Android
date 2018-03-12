package com.lateroad.buber.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lateroad.buber.R;
import com.lateroad.buber.activity.TripsActivity;

/**
 * Created by LateRoad on 12.03.2018.
 */

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripViewHolder> {

    final private ItemClickListener mOnClickListener;

    private int mNumberItems;

    private String[] mTripsData;


    public interface ItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }


    public TripsAdapter(int numberOfItems, ItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
    }

    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForTripItem = R.layout.trip_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForTripItem, viewGroup, shouldAttachToParentImmediately);
        return new TripViewHolder(view);
    }


    @Override
    public void onBindViewHolder(TripViewHolder holder, int position) {
        String date = mTripsData[position];
        holder.tripItemDataView.setText(date);
    }

    public void setTripsDataData(String[] tripsData) {
        mTripsData = tripsData;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mNumberItems;
    }


    class TripViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView tripItemDataView;


        public TripViewHolder(View itemView) {
            super(itemView);
            tripItemDataView = (TextView) itemView.findViewById(R.id.tv_trip_data);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
