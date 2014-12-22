package com.example.katecatlin.detroitlive.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.activities.ListActivity;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
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
//        ListActivity_.intent(this).start();
    }

}
