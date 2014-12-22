package com.example.katecatlin.detroitlive.interfaces;

import com.example.katecatlin.detroitlive.models.Concert;

import java.util.List;

/**
 * Created by katecatlin on 12/19/14.
 */
public interface IndividualApiRequestCallback {

    public void onSuccess(List<Concert> concertList);
    public void onError();

}
