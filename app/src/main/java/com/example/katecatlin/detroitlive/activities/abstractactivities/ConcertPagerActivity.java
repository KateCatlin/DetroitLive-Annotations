package com.example.katecatlin.detroitlive.activities.abstractactivities;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.katecatlin.detroitlive.R;
import com.example.katecatlin.detroitlive.fragments.ConcertDetailFragment_;
import com.example.katecatlin.detroitlive.models.Concert;

import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;

@EActivity
public class ConcertPagerActivity extends BaseActivity {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private ArrayList<Concert> concerts;

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        setViewPager();
    }

    public void setViewPager() {
        Bundle extras = getIntent().getExtras();
        int currentPosition = extras.getInt("position");
        concerts = extras.getParcelableArrayList("data");

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public int getCount() {
                return concerts.size();
            }

            @Override
            public Fragment getItem(int position) {
                Concert concert = concerts.get(position);
                return ConcertDetailFragment_.newInstance(concert);
            }
        });

        viewPager.setCurrentItem(currentPosition);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged (int state) { }

            public void onPageScrolled (int pos, float posOffset, int posOffsetPixels) { }

            public void onPageSelected (int currentPosition) {
                Concert concert = concerts.get(currentPosition);
                if (concert.getArtist1() != null) {
                    setTitle(concert.getArtist1());
                }

            }
        });
    }
}