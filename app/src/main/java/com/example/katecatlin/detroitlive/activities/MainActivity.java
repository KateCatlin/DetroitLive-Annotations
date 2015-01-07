package com.example.katecatlin.detroitlive.activities;

import android.util.Log;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.activities.abstractactivities.BaseActivity;
import com.example.katecatlin.detroitlive.fragments.ConcertListFragment;
import com.example.katecatlin.detroitlive.fragments.ConcertListFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by katecatlin on 12/19/14.
 */

@EActivity(R.layout.activity_base)
public class MainActivity extends BaseActivity {

    @AfterViews
    void loadConcertListFragment () {

        ConcertListFragment concertListFragment = ConcertListFragment_.builder().build();

        int result = getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, concertListFragment)
                .commit();


        Log.d("MainActivity", "load concert list fragment = " + result);
    }
}
