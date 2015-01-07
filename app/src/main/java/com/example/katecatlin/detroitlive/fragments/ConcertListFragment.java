package com.example.katecatlin.detroitlive.fragments;

import android.app.Fragment;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.adapters.ConcertListAdapter;
import com.example.katecatlin.detroitlive.interfaces.MasterApiRequestCallback;
import com.example.katecatlin.detroitlive.models.Concert;
import com.example.katecatlin.detroitlive.parsers.SortConcertsByDate;
import com.example.katecatlin.detroitlive.requests.MasterRequest;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 12/19/14.
 */
@EFragment (R.layout.fragment_concert_list)
public class ConcertListFragment extends Fragment implements MasterApiRequestCallback {
    private ArrayList<Concert> sortedConcerts;


//    public static ConcertListFragment newInstance() {
//        ConcertListFragment fragment = new ConcertListFragment_();
//        return fragment;
//    }

    @ViewById (R.id.concert_list)
    ListView concertList;

    @Bean
    protected ConcertListAdapter concertListAdapter;

    protected MasterRequest masterRequest = new MasterRequest(this);

    @AfterViews
    public void attachAdapter () {
        Log.d("LOG_TAG", "ConcertList still null? 1 " + concertList);
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
}