package com.example.katecatlin.detroitlive.activities;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.fragments.ConcertListFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by katecatlin on 12/19/14.
 */

@EActivity(R.layout.activity_base)
public class ListActivity extends BaseActivity {

    @AfterViews
    public void loadConcertListFragment () {

        Fragment fragment = getFragmentManager().findFragmentByTag(ConcertListFragment.FRAG_TAG);
        if(fragment != null) {
            getFragmentManager().beginTransaction().show(fragment).commit();
            return;
        }

        ConcertListFragment concertListFragment = new ConcertListFragment();

        int result = getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, concertListFragment, ConcertListFragment.FRAG_TAG)
                .commit();
    }
}
