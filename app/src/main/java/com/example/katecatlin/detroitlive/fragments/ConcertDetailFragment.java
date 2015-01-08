package com.example.katecatlin.detroitlive.fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.models.Concert;

import org.androidannotations.annotations.EFragment;

/**
 * Created by katecatlin on 1/7/15.
 */

@EFragment
public class ConcertDetailFragment extends android.support.v4.app.Fragment {

        private static final String ARG_CONCERT_ENTRY = "arg_concert_entry";
        private TextView detailArtistTextView, detailDateTextView, detailTimeTextView, detailVenueTextView,
                detailAddressTextView, detailCityTextView, detailZipCodeTextView, detailUrlTextView,
                featuringTextView, detailArtist2TextView, detailArtist3TextView;
        private Button ticketButton;

        public static ConcertDetailFragment newInstance(Concert concert) {

            Bundle args = new Bundle();
            args.putParcelable(ARG_CONCERT_ENTRY, concert);

            ConcertDetailFragment concertDetailFragment = new ConcertDetailFragment();
            concertDetailFragment.setArguments(args);
            Log.d("LOG_TAG", "concert is " + concert.getArtist1());

            return concertDetailFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            super.onCreate(savedInstanceState);
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            ViewGroup view = (ViewGroup) inflater.inflate(
                    R.layout.fragment_detail_view, container, false);

            detailArtistTextView = (TextView) view.findViewById(R.id.detailArtistTextView);
            detailDateTextView = (TextView) view.findViewById(R.id.detailDateTextView);
            detailTimeTextView = (TextView) view.findViewById(R.id.detailTimeTextView);
            detailVenueTextView = (TextView) view.findViewById(R.id.detailVenueTextView);
            detailAddressTextView = (TextView) view.findViewById(R.id.detailAddressTextView);
            detailCityTextView = (TextView) view.findViewById(R.id.detailCityTextView);
            detailZipCodeTextView = (TextView) view.findViewById(R.id.detailZipCodeTextView);
            detailUrlTextView = (TextView) view.findViewById(R.id.detailUrlTextView);
            featuringTextView = (TextView) view.findViewById(R.id.featuringTextView);
            detailArtist2TextView = (TextView) view.findViewById(R.id.detailArtist2TextView);
            detailArtist3TextView = (TextView) view.findViewById(R.id.detailArtist3TextView);
            ticketButton  = (Button) view.findViewById(R.id.Ticket_Button);

            return view;
        }


        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            final Concert concert = getArguments().getParcelable(ARG_CONCERT_ENTRY);

            Log.d("LOG_TAG", "concert is " + concert.getArtist1());

            if (concert != null) {

                detailArtistTextView.setText(concert.getArtist1());
                detailDateTextView.setText(concert.convertDateTimetoDate(concert.getDateTime()));
                detailVenueTextView.setText(concert.getVenue());
                detailAddressTextView.setText(concert.getAddress());
                detailCityTextView.setText(concert.getCity());
                detailZipCodeTextView.setText(concert.getZipCode());
                detailUrlTextView.setText(concert.getTicketUrl());
                detailArtist2TextView.setText(concert.getArtist2());
                detailArtist3TextView.setText(concert.getArtist3());
                if (concert.getArtist2() == null || concert.getArtist2().equals("")) {
                    featuringTextView.setText("");
                }
                if (concert.convertDateTimetoTime(concert.getDateTime()).toString().equals("0:00")) {
                    detailTimeTextView.setVisibility(View.GONE);
                } else {
                    detailTimeTextView.setText(concert.convertDateTimetoTime(concert.getDateTime()));
                }

                ticketButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        if (!concert.getTicketUrl().equals("")) {
                            openWebURL(concert.getTicketUrl());
                        }
                        else if (!concert.getVenueURL().equals("")) {
                            openWebURL(concert.getVenueURL());
                        } else {
                            Toast.makeText(getActivity(), "Sorry, URL not provided!", Toast.LENGTH_SHORT);
                        }
                    }
                });
            } else {
                throw new IllegalStateException("Something went wrong passing concert model to detail fragment!");
            }
        }


        public void openWebURL(String URL ) {

            Intent internetIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
            internetIntent.setComponent(new ComponentName("com.android.browser","com.android.browser.BrowserActivity"));
            internetIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getActivity().startActivity(internetIntent);

        }
    }
