package com.example.katecatlin.detroitlive;

import android.app.Application;
import android.util.Log;

import org.androidannotations.api.BackgroundExecutor;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by katecatlin on 12/22/14.
 */
public class DetroitLive extends Application implements ApplicationProvider.ApplicationHelper {

    @Override
    public void onCreate(){
        super.onCreate();
        ApplicationProvider.setApplicationHelper(this);
        installHttpCache();
        setThreadPoolExecutor();
    }

    @Override
    public int dpToPixels(final int dpUnits) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) ((dpUnits * scale) + 0.5);
    }

    @Override
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    private void setThreadPoolExecutor() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        BackgroundExecutor.setExecutor(executorService);
    }

    private void installHttpCache() {
        try {
            File httpCacheDir = new File(getCacheDir(), "http");
            long httpCacheSize = 15 * 1024 * 1024; // 15 MiB
            Class.forName("android.net.http.HttpResponseCache")
                    .getMethod("install", File.class, long.class)
                    .invoke(null, httpCacheDir, httpCacheSize);
        } catch (Exception httpResponseCacheNotAvailable) {
            Log.d("LOG_TAG", "Config issue");
        }
    }
}
