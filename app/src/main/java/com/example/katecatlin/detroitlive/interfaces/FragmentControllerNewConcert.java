package com.example.katecatlin.detroitlive.interfaces;

import android.app.Fragment;

import com.example.katecatlin.detroitlive.models.Concert;

/**
 * Created by katecatlin on 12/19/14.
 */
public interface FragmentControllerNewConcert {

    public void changeFragment(Fragment fragment, boolean addToBackstack, Concert concert);

}
