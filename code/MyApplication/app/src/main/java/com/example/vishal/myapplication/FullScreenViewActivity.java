package com.example.vishal.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by vishal on 4/12/17.
 */
public class FullScreenViewActivity extends AppCompatActivity {
    ViewPager viewPager;
    FullScreenViewAdapter adapter;
    Utils utils;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_view);

        viewPager = (ViewPager)findViewById(R.id.pager);
        utils = new Utils(this.getBaseContext(),this.getAssets());

        // get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("position",0);
        FullScreenViewAdapter imageAdapter = new FullScreenViewAdapter(this, utils.getFilePaths());
        adapter = new FullScreenViewAdapter(FullScreenViewActivity.this,
                utils.getFilePaths());


        viewPager.setAdapter(adapter);

        // displaying selected image first

        viewPager.setCurrentItem(position);

    }

}