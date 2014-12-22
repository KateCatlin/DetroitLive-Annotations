package com.example.katecatlin.detroitlive.parsers;

import com.example.katecatlin.detroitlive.models.Concert;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by katecatlin on 12/19/14.
 */
public class SortConcertsByDate {

    public SortConcertsByDate () {
    }


    public List<Concert> sortConcerts (List<Concert> returnedConcerts) {

        Collections.sort(returnedConcerts, new Comparator<Concert>() {

            public int compare(Concert concert1, Concert concert2) {

                if (concert1.getDateTime() == null || concert2.getDateTime() == null) {
                    return 0;
                }

                return concert1.getDateTime().compareTo(concert2.getDateTime());
            }
        });

        return returnedConcerts;
    }
}
