package com.lateroad.buber.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lateroad.buber.R;
import com.lateroad.buber.entity.Card;
import com.lateroad.buber.utilities.JsonUtils;
import com.lateroad.buber.utilities.NetworkUtils;

import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends Activity {

    @BindView(R.id.tv_card_number) TextView mCardsListTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setTitle("PaymentActivity");
        ButterKnife.bind(this);

        new PaymentActivity.SearchTask().execute("cards");
    }

    // TODO: Add payment method.
    public void addPayment(View view) {
    }

    // TODO: Replace AsynkTask with something better.
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
                    mCardsListTextView.append("**** " + card.getLastDigits() + "\n\n\n");
                }
            }
            //TODO: Add card == null scenario.
            else {
            }
        }

    }
}
