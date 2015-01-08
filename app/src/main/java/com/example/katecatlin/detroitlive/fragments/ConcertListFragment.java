package com.example.katecatlin.detroitlive.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.activities.abstractactivities.ConcertPagerActivity;
import com.example.katecatlin.detroitlive.activities.abstractactivities.ConcertPagerActivity_;
import com.example.katecatlin.detroitlive.adapters.ConcertListAdapter;
import com.example.katecatlin.detroitlive.interfaces.MasterApiRequestCallback;
import com.example.katecatlin.detroitlive.models.Concert;
import com.example.katecatlin.detroitlive.parsers.SortConcertsByDate;
import com.example.katecatlin.detroitlive.requests.MasterRequest;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 12/19/14.
 */
@EFragment (R.layout.fragment_concert_list)
public class ConcertListFragment extends Fragment implements MasterApiRequestCallback {
    private ArrayList<Concert> sortedConcerts;

    @ViewById (R.id.concert_list)
    ListView concertList;

    @Bean
    protected ConcertListAdapter concertListAdapter;

    protected MasterRequest masterRequest = new MasterRequest(this);

    @AfterViews
    public void attachAdapter () {
        concertListAdapter = new ConcertListAdapter(getActivity());
        concertList.setAdapter(concertListAdapter);
        refreshConcerts();
    }

    public void refreshConcerts () {
        masterRequest = MasterRequest.getMasterRequest(this);
        masterRequest.loadConcerts(getActivity());
    }

    @Override
    public void onSuccess(List<Concert> returnedConcerts) {
        Toast.makeText(getActivity(), "OnSuccess!", Toast.LENGTH_SHORT).show();
        if (isAdded()) {

            SortConcertsByDate sortConcertsByDate = new SortConcertsByDate();
            sortedConcerts = new ArrayList<Concert>(sortConcertsByDate.sortConcerts(returnedConcerts));

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    concertListAdapter.clear();
                    concertListAdapter.addAll(sortedConcerts);
                    concertListAdapter.notifyDataSetChanged();
                }
            });
        }

    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "Error loading concerts, press back and try again!", Toast.LENGTH_LONG).show();
    }

    @ItemClick
    void concertListItemClicked(int position) {
        Intent concertPagerIntent = new Intent(getActivity(), ConcertPagerActivity_.class);
        Bundle dataBundle = new Bundle();
        dataBundle.putParcelableArrayList("data", sortedConcerts);
        dataBundle.putInt("position", position);

        concertPagerIntent.putExtras(dataBundle);
        startActivity(concertPagerIntent);
    }
}