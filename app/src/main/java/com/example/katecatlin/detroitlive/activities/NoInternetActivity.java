package com.example.katecatlin.detroitlive.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.fragments.NoInternetFragment;
import com.example.katecatlin.detroitlive.fragments.NoInternetFragment_;
import com.example.katecatlin.detroitlive.utils.NetworkUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by katecatlin on 12/19/14.
 */
@EActivity(R.layout.activity_base)
public class NoInternetActivity extends BaseActivity {

    @AfterViews
    protected void addEmergencyFragment() {
        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragmentContainer,
                        NoInternetFragment_.builder()
                                .build()
                ).commit();
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
//                Intent restartApp = ListActivity_.intent(NoInternetActivity.this).flags(Intent.FLAG_ACTIVITY_CLEAR_TOP).get();
//                startActivity(startTabActivity);
                finish();
            }
        }
    };

}
