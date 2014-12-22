package com.example.katecatlin.detroitlive.requests;

import android.app.Activity;

import com.example.katecatlin.detroitlive.interfaces.IndividualApiRequestCallback;
import com.example.katecatlin.detroitlive.interfaces.MasterApiRequestCallback;
import com.example.katecatlin.detroitlive.models.Concert;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 12/19/14.
 */

public class MasterRequest implements IndividualApiRequestCallback {

    public static Integer apisReturned = 0;
    List<Concert> upcomingConcerts = new ArrayList<Concert>();
    private MasterApiRequestCallback thisMasterAPIRequestCallback;
    private static MasterRequest masterRequest;


    public static MasterRequest getMasterRequest(MasterApiRequestCallback masterAPIRequestCallback) {
        if (masterRequest == null) {
            masterRequest = new MasterRequest(masterAPIRequestCallback);
        }
        return masterRequest;
    }


    public MasterRequest(MasterApiRequestCallback masterAPIRequestCallback) {
        thisMasterAPIRequestCallback = masterAPIRequestCallback;
    }


    public void loadConcerts(Activity activity) {

        upcomingConcerts.clear();

        JSONRequest jsonRequest = JSONRequest.getJsonRequest(this);
        jsonRequest.getConcerts();

        ParseRequest parseRequest = ParseRequest.getParseRequest(activity, this);
        parseRequest.getConcertsFromParse();
    }


    public void refreshConcerts (List<Concert> returnedConcerts) {

        if (returnedConcerts != null) {
            for (int i = 0; i < returnedConcerts.size(); i++) {
                upcomingConcerts.add(returnedConcerts.get(i));
            }
        }

        if (apisReturned == 2) {
            if (upcomingConcerts.get(0) != null) {
                apisReturned = 0;
                thisMasterAPIRequestCallback.onSuccess(upcomingConcerts);
            } else {
                thisMasterAPIRequestCallback.onError();
            }
        }
    }

    @Override
    public void onSuccess(List<Concert> concertList) {
        apisReturned++;
        refreshConcerts(concertList);
    }


    @Override
    public void onError() {
        apisReturned++;
        refreshConcerts(null);
    }
}
