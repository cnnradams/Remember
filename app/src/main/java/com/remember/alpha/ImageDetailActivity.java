package com.remember.alpha;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.remember.alpha.adapters.Memories;
import com.remember.alpha.adapters.MemoriesManager;

import java.util.List;

/**
 * Created by Christopher on 11/01/2016.
 * Used for the viewpager when a photo is opened.
 */
public class ImageDetailActivity extends FragmentActivity {
    public static final String EXTRA_IMAGE = "extra_image";

    private ImagePagerAdapter mAdapter;
    private ViewPager mPager;

    // A static dataset to back the ViewPager adapter


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager); // Contains just a ViewPager

        Intent intent = getIntent();
        String eventID = intent.getExtras().getString("id");
        int startingPosition = intent.getExtras().getInt("position");

        List<Memories> imageBitmaps = MemoriesManager.getInstance(eventID).getMemories(this, eventID);

        mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), imageBitmaps.size(),eventID);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(startingPosition);
    }

    public static class ImagePagerAdapter extends FragmentStatePagerAdapter {
        private final int mSize;
        private String eventID;

        public ImagePagerAdapter(FragmentManager fm, int size, String eventID) {
            super(fm);
            mSize = size;
            this.eventID = eventID;
        }

        @Override
        public int getCount() {
            return mSize;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView((View)object);
        }


        @Override
        public Fragment getItem(int position) {
            return ImageDetailFragment.newInstance(position, eventID);
        }
    }
}