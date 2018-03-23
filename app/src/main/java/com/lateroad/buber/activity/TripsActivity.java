package com.lateroad.buber.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lateroad.buber.R;
import com.lateroad.buber.adapter.TripsAdapter;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.utilities.JsonUtils;
import com.lateroad.buber.utilities.NetworkUtils;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripsActivity extends Activity {

    @BindView(R.id.rv_trips) RecyclerView mRecyclerView;
    @BindView(R.id.tv_error_message_display) TextView mErrorMessageDisplay;
    @BindView(R.id.pb_loading_indicator) ProgressBar mLoadingIndicator;

    private TripsAdapter mTripsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mTripsAdapter = new TripsAdapter();
        mRecyclerView.setAdapter(mTripsAdapter);
        loadTripsData();
    }


    private void loadTripsData() {
        new SearchTask().execute("orders");
    }

    private void showJsonDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    // TODO: Replace AsyncTask with something better.
    private class SearchTask extends AsyncTask<String, Void, List> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Order> doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }

            String path = params[0];
            URL requestUrl = NetworkUtils.buildUrl(path);
            JsonUtils<Order> jsonUtils = new JsonUtils<>();
            try {
                String jsonResponse = NetworkUtils
                        .getResponseFromHttpUrl(requestUrl);


                return jsonUtils
                        .getEntityFromJson(jsonResponse, Order.class);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List orders) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (orders != null) {
                showJsonDataView();
                mTripsAdapter.setData(orders);
            } else {
                showErrorMessage();
            }
        }
    }
}
