package com.example.katecatlin.detroitlive.parsers;

import com.example.katecatlin.detroitlive.models.Concert;
import com.parse.ParseObject;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by katecatlin on 12/19/14.
 */
public class ParseObjectParser {

    public static List<Concert> parseParseObject(ArrayList<ParseObject> parseObjects) {

        String venue, address, city, zipCode, venueURL, artist1, artist2, artist3, ticketUrl;
        DateTime dateTime;
        Date tempDate;
        Concert concert;


        List<Concert> concertsList = new ArrayList<Concert>();

        for (ParseObject x : parseObjects) {

            tempDate = x.getDate("Date");
            dateTime = new DateTime(tempDate);
            venue = x.getString("Venue");
            address = x.getString("Address");
            city = x.getString("City");
            zipCode = x.getString("ZipCode");
            venueURL = x.getString("VenueURL");
            artist1 = x.getString("Artist1");
            artist2 = x.getString("Artist2");
            artist3 = x.getString("Artist3");
            ticketUrl = x.getString("TicketUrl");

            concert = new Concert(dateTime, venue, address, city, zipCode, venueURL, artist1, artist2, artist3, ticketUrl);

            concertsList.add(concert);
        }
        return concertsList;
    }
}
