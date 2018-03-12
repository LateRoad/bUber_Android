package com.lateroad.buber.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lateroad.buber.R;
import com.lateroad.buber.entity.Card;
import com.lateroad.buber.entity.Order;
import com.lateroad.buber.utilities.JsonUtils;
import com.lateroad.buber.utilities.NetworkUtils;

import java.net.URL;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    private TextView mCardsListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mCardsListTextView = (TextView) findViewById(R.id.tv_card_number);
        new PaymentActivity.SearchTask().execute("cards");
    }

    @SuppressLint("StaticFieldLeak")
    private class SearchTask extends AsyncTask<String, Void, List<Card>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Card> doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }

            String path = params[0];
            URL requestUrl = NetworkUtils.buildUrl(path);
            JsonUtils<Card> jsonUtils = new JsonUtils<>();
            try {
                String jsonResponse = NetworkUtils
                        .getResponseFromHttpUrl(requestUrl);


                return jsonUtils
                        .getEntityFromJson(jsonResponse, Card.class);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Card> cards) {
            if (cards != null) {
                for (Card card : cards) {
                    mCardsListTextView.append(card.getHashNumber() + "\n\n\n");
                }
            } else {
            }
        }
    }
}
