package com.example.katecatlin.detroitlive;

import android.app.Application;

/**
 * Created by katecatlin on 12/19/14.
 */
public class ApplicationProvider {

    public interface ApplicationHelper {
        public int dpToPixels(final int dpUnits);
        public long getCurrentTimeMillis();
    }

    private static ApplicationHelper helper;

    public static ApplicationHelper getApplicationHelper() {
        return (ApplicationHelper) getHelper();
    }

    public static Application getApplicationContext() {
        return (Application) getHelper();
    }

    public static void setApplicationHelper(ApplicationHelper helper) {
        ApplicationProvider.helper = helper;
    }

    private static Object getHelper() {
        if (helper == null)
            throw new RuntimeException("Application helper is null");
        else
            return helper;
    }

}