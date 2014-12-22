package com.example.katecatlin.detroitlive.activities.abstractactivities;

import android.app.Fragment;
import android.app.FragmentTransaction;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.activities.BaseActivity;

import org.androidannotations.annotations.EActivity;

/**
 * Created by katecatlin on 12/19/14.
 */
@EActivity
public class MultipleFragmentActivity extends BaseActivity {

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void changeFragment(Fragment fragment, boolean addToBackstack) {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }


}
