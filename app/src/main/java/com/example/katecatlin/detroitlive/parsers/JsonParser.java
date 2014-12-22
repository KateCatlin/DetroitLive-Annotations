package com.example.katecatlin.detroitlive.parsers;

import com.example.katecatlin.detroitlive.models.Concert;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by katecatlin on 12/19/14.
 */

public class JsonParser {

    public static List<Concert> parseJSONObject (JSONObject jsonObject) {

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("Events");

            List <Concert> concertsList = new ArrayList<Concert>();

            for (int index = 0; index < jsonArray.length(); index++) {
                JSONObject concertEntry = jsonArray.getJSONObject(index);
                Concert newConcert = createConcertModelFromJsonInfo (concertEntry);
                concertsList.add(newConcert);
            }
            return  concertsList;

        } catch (JSONException e) {
            return new ArrayList<Concert>();
        }
    }

    private static Concert createConcertModelFromJsonInfo (JSONObject concertsJsonObject) throws JSONException {

        String venue, address, city, zipCode, venueURL, artist1, artist2, artist3, ticketUrl;
        DateTime dateTime;
        Concert concert;
        JSONObject venueObject, artistObject;
        JSONArray artistArray;

        dateTime = new DateTime(concertsJsonObject.optString("Date"));

        venueObject = concertsJsonObject.getJSONObject("Venue");

        venue = venueObject.optString("Name", "Unknown Venue");
        address = venueObject.optString("Address", "Unknown Address");
        city = venueObject.optString("City", "Unknown City");
        zipCode = venueObject.optString("ZipCode", "Unknown ZIP code");
        venueURL = venueObject.optString("Url", "No URL");

        artistArray = concertsJsonObject.getJSONArray("Artists");

        artistObject = artistArray.getJSONObject(0);
        artist1 = artistObject.optString("Name", "Unknown Artist");
        artist2 = "";
        artist3 = "";

        if (artistArray.length() > 1) {
            artistObject = artistArray.getJSONObject(1);
            artist2 = artistObject.optString("Name");
        }

        if (artistArray.length() > 2) {
            artistObject = artistArray.getJSONObject(2);
            artist3 = artistObject.optString("Name");
        }

        ticketUrl = concertsJsonObject.optString("TicketUrl", "Unknown URL");

        concert = new Concert(dateTime,venue, address, city, zipCode,
                venueURL, artist1, artist2, artist3, ticketUrl);


        return concert;
    }
}

