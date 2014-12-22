package com.example.katecatlin.detroitlive.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.utils.ActivityUtil;
import com.example.katecatlin.detroitlive.utils.NetworkUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

@EActivity
public abstract class BaseActivity extends ActionBarActivity {

    public static Activity currentActivity;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBar();
    }

    @AfterViews


    @Override
    public void onStart() {
        super.onStart();
        checkNoInternet();
        currentActivity = this;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (currentActivity == this) {
            currentActivity = null;
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @UiThread
    public void showProgressDialog(String message) {
        if (!ActivityUtil.isActivityValid(this)) {
            return;
        }

        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
        }

        progressDialog.setMessage(message);
        progressDialog.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setActionBar() {

        ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(R.string.Heading);

        actionBar.show();
    }

    public void goHome () {
        Intent goHome = new Intent(this, ListActivity.class);
        goHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(goHome);
        overridePendingTransition(0, R.anim.slide_in_right);
    }

    private void checkNoInternet() {
        if (!NetworkUtil.checkInternetConnection()) {
            NoInternetActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                goHome();
                break;
            case R.id.action_refresh:
                goHome();
                break;
            case R.id.action_new_concert:
                Intent addConcertIntent = new Intent (this, AddConcertActivity.class );
                startActivity(addConcertIntent);
                overridePendingTransition(0, R.anim.slide_in_left);
                break;

        }
        return true;
    }


}