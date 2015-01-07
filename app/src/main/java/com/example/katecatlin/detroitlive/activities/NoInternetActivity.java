package com.example.katecatlin.detroitlive.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.activities.abstractactivities.BaseActivity;
import com.example.katecatlin.detroitlive.utils.NetworkUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by katecatlin on 12/19/14.
 */
@EActivity(R.layout.activity_base)
public class NoInternetActivity extends BaseActivity {

    @AfterViews
    protected void addEmergencyFragment() {
        android.app.FragmentManager fm = getFragmentManager();
//        fm.beginTransaction()
//                .replace(R.id.fragmentContainer,
//                        NoInternetFragment.builder()
//                                .build()
//                ).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateChangedReceiver);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private BroadcastReceiver networkStateChangedReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (NetworkUtil.checkInternetConnection()) {
                //FIX ME FIX ME FIX ME
//                Intent restartApp = MainActivity_.intent(NoInternetActivity.this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).get();
//                startActivity(startTabActivity);
                finish();
            }
        }
    };

}
