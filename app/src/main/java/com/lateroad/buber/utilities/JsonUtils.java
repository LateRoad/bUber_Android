package com.lateroad.buber.utilities;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public final class JsonUtils {

    public static String[] getSimpleStringsFromJson(Context context, String jsonStr)
            throws JSONException {
        System.out.println(jsonStr);
        final String CLIENT_LOGIN = "clientLogin";
        final String DRIVER_LOGIN = "driverLogin";

        final String MONEY = "money";

        final String STATUS = "status";
        final String DATE = "date";

        final String ORIGIN = "origin";
        final String DESTINATION = "destination";

        String[] parsedData = null;

        JSONArray jsonArray = new JSONArray(jsonStr);

        parsedData = new String[jsonArray.length()];


        for (int i = 0; i < jsonArray.length(); i++) {
            String date;
            String money;
            String clientLogin;
            String driverLogin;
            String origin;
            String destination;

            JSONObject trip = jsonArray.getJSONObject(i);


            date = trip.getString(DATE);
            money = trip.getString(MONEY);
            clientLogin = trip.getString(CLIENT_LOGIN);
            driverLogin = trip.getString(DRIVER_LOGIN);

            parsedData[i] = date + " - " + money + " - " + clientLogin + " - " + driverLogin;
        }

        return parsedData;
    }

    public static ContentValues[] getFullDataFromJson(Context context, String jsonStr) {
        return null;
    }
}