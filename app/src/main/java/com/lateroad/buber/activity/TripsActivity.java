package com.lateroad.buber.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lateroad.buber.R;
import com.lateroad.buber.adapter.TripsAdapter;
import com.lateroad.buber.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class TripsActivity extends AppCompatActivity implements TripsAdapter.ItemClickListener {

    private TextView mSearchResultsTextView;

    private TextView mQueryDisplayTextView;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    private Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips);

        mSearchResultsTextView = findViewById(R.id.tv_users_search_results_json);
        mQueryDisplayTextView = findViewById(R.id.tv_url_display);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        makeSearch();
    }

    private void makeSearch() {
        URL searchUrl = NetworkUtils.buildUrl("orders");
        if (searchUrl != null) {
            mQueryDisplayTextView.setText(searchUrl.toString());
            new SearchTask().execute(searchUrl);
        }
    }

    private void showJsonDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (mToast != null) {
            mToast.cancel();
        }
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }


    @SuppressLint("StaticFieldLeak")
    private class SearchTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String searchResults = null;
            try {
                searchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return searchResults;
        }

        @Override
        protected void onPostExecute(String searchResults) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (searchResults != null && !searchResults.equals("")) {
                showJsonDataView();
                mSearchResultsTextView.setText(searchResults);
            } else {
                showErrorMessage();
            }
        }
    }
}
