package com.example.katecatlin.detroitlive.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.models.Concert;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;
import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * Created by katecatlin on 12/20/14.
 */
@EBean
public class ConcertListAdapter extends ArrayAdapter<Concert> {

    public ConcertListAdapter (Context context) {
        super(context, R.layout.rows_concert_listings);
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public int getItemViewType (int position) {
        return (position == 0) ? 0 : 1;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        int type = getItemViewType(position);
        final int FIRST_CONCERT_VIEW = 0;
        final int OTHER_CONCERT_VIEW = 1;

        if (convertView == null) {

            switch (type) {
                case FIRST_CONCERT_VIEW:
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.first_row_concert_listing, parent, false);
                    break;
                case OTHER_CONCERT_VIEW:
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.rows_concert_listings, parent, false);

                    break;
            }

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Concert concert = getItem(position);

        viewHolder.textView_artist1.setText(concert.getArtist1());
        viewHolder.textView_city.setText(concert.getCity());
        viewHolder.textView_venue.setText(concert.getVenue());
        DateTime dateTime = concert.getDateTime();
        viewHolder.textView_date.setText(concert.convertDateTimetoDate(dateTime));

        return convertView;
    }


    public static class ViewHolder {

        private TextView textView_artist1;
        private TextView textView_date;
        private TextView textView_venue;
        private TextView textView_city;

        public ViewHolder(View rootView) {
            this.textView_artist1 = (TextView) rootView.findViewById(R.id.TextView_Artist1);
            this.textView_date = (TextView) rootView.findViewById(R.id.TextView_Date);
            this.textView_venue = (TextView) rootView.findViewById(R.id.TextView_Venue);
            this.textView_city = (TextView) rootView.findViewById(R.id.TextView_City);
        }
    }

}
