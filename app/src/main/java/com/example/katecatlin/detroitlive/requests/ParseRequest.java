package com.example.katecatlin.detroitlive.requests;

import android.app.Activity;
import android.util.Log;

import com.example.katecatlin.detroitlive.interfaces.IndividualApiRequestCallback;
import com.example.katecatlin.detroitlive.models.Concert;
import com.example.katecatlin.detroitlive.parsers.ParseObjectParser;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by katecatlin on 12/19/14.
 */
public class ParseRequest {

    private static String APPLICATION_ID = "Farfo7O7tFBYG7ZqhhUr3Qxp0rzUGDbqiYNJaWwX";
    private static String CLIENT_KEY = "3AN5tiVBtJ24e5J0RUx1DNf1cpeXBJiQqrdigEif";
    private static ParseRequest parseRequest;
    private IndividualApiRequestCallback individualApiRequestCallback;


    public ParseRequest(Activity currentActivity, IndividualApiRequestCallback individualApiRequestCallback) {
        Parse.initialize(currentActivity, APPLICATION_ID, CLIENT_KEY);
        this.individualApiRequestCallback = individualApiRequestCallback;
    }


    public static ParseRequest getParseRequest (Activity activity, IndividualApiRequestCallback individualApiRequestCallback) {
        if (parseRequest == null) {
            parseRequest = new ParseRequest(activity, individualApiRequestCallback);
        }
        return parseRequest;
    }


    public void postConcertToParse(Concert newConcert) {

        Log.d("LOG_TAG", "newConcertObject is " + newConcert.getArtist1() + " " + newConcert.getAddress());
        ParseObject newConcertObject = new ParseObject("Concert");
        newConcertObject.put("Date", newConcert.getDateTime().toDate());
        newConcertObject.put("Venue", newConcert.getVenue());
        newConcertObject.put("Address", newConcert.getAddress());
        newConcertObject.put("City", newConcert.getCity());
        newConcertObject.put("ZipCode", newConcert.getZipCode());
        newConcertObject.put("VenueURL", newConcert.getVenueURL());
        newConcertObject.put("Artist1", newConcert.getArtist1());
        newConcertObject.put("Artist2", newConcert.getArtist2());
        newConcertObject.put("Artist3", newConcert.getArtist3());
        newConcertObject.put("TicketUrl", newConcert.getTicketUrl());
        newConcertObject.saveInBackground();
    }


    public void getConcertsFromParse() {

        List<ParseObject> parseObjectList = null;
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Concert");
        ArrayList<ParseObject> parseObjects = new ArrayList<ParseObject>();
        List<Concert> concerts = null;

        try {
            parseObjectList = parseQuery.find();

            for (ParseObject x: parseObjectList) {
                Date date = new Date();
                Date currentDate = new Date();

                date = x.getDate("Date");
                if (date.after(currentDate)) {
                    parseObjects.add(x);
                }
            }

            ParseObjectParser parseObjectParser = new ParseObjectParser();
            concerts = parseObjectParser.parseParseObject(parseObjects);

        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        if (concerts != null) {
            individualApiRequestCallback.onSuccess(concerts);
        } else {
            individualApiRequestCallback.onError();
        }
    }


    public static ParseRequest getEstablishedParseRequest (Activity activity) {
        return parseRequest;
    }
}
