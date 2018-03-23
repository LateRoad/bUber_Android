package com.lateroad.buber.utilities;

import android.content.Context;
import android.content.Intent;

import com.lateroad.buber.activity.Activity;

/**
 * Created by LateRoad on 23.03.2018.
 */

public class ActivityUtils {
    private Activity activity;

    public ActivityUtils(Activity activity) {
        this.activity = activity;
    }

    public void switchActivity(Activity destination) {
        Context context = activity;
        Class destinationActivity = destination.getClass();
        Intent startChildActivityIntent = new Intent(context, destinationActivity);
        activity.startActivity(startChildActivityIntent);
    }
}
