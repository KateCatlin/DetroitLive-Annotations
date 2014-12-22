package com.example.katecatlin.detroitlive.fragments;

import android.app.Fragment;
import android.app.ListFragment;

import com.example.katecatlin.detroitlive.adapters.ConcertListAdapter;
import com.example.katecatlin.detroitlive.interfaces.MasterApiRequestCallback;
import com.example.katecatlin.detroitlive.models.Concert;
import com.example.katecatlin.detroitlive.requests.MasterRequest;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 12/19/14.
 */
@EFragment
public class ConcertListFragment extends ListFragment implements MasterApiRequestCallback {
    private ArrayList<Concert> sortedConcerts;
    private ConcertListAdapter concertListAdapter;

    public static final String FRAG_TAG = "ConcertListFragment";

    @Bean
    protected MasterRequest masterRequest;

    @AfterViews
    private void attachAdapter () {
        concertListAdapter = new ConcertListAdapter(getActivity());
        setListAdapter(concertListAdapter);
        refreshConcerts();
    }

    public void refreshConcerts () {
        masterRequest = MasterRequest.getMasterRequest(this);
        masterRequest.loadConcerts(getActivity());
    }

    @Override
    public void onSuccess(List<Concert> concertList) {
    }

    @Override
    public void onError() {
    }
}
