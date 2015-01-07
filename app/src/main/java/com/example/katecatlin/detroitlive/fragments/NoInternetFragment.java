package com.example.katecatlin.detroitlive.fragments;

import android.app.Fragment;
import android.widget.Button;

import com.example.katecatlin.detroitlive.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_no_internet)
public class NoInternetFragment extends Fragment {

    @ViewById
    protected Button tryAgainButton;

    public NoInternetFragment() {
        // Required empty public constructor
    }

    @Click
    public void tryAgainButton() {
        //FIX ME FIX ME FIX ME
//        MainActivity_.intent(this).start();
    }

}
