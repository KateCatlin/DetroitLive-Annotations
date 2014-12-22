package com.example.katecatlin.detroitlive.utils;

import android.app.Activity;
import android.os.Build;

/**
 * Created by katecatlin on 12/19/14.
 */
public class ActivityUtil {

    private ActivityUtil() { }

    public static boolean isActivityValid(Activity activity) {
        if (activity == null) {
            return false;
        } else {
            if (Build.VERSION.SDK_INT >= 17) {
                return !activity.isFinishing() && !activity.isDestroyed();
            } else {
                return !activity.isFinishing();
            }
        }
    }
}
