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

<<<<<<< HEAD
/*
 * Copyright (c) 2017 Vishal Srivastava<vsriva10@asu.edu>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Created by vishal on 4/12/17.
 */

=======
/**
 * Created by vishal on 4/12/17.
 */
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
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
<<<<<<< HEAD
=======
        FullScreenViewAdapter imageAdapter = new FullScreenViewAdapter(this, utils.getFilePaths());
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
        adapter = new FullScreenViewAdapter(FullScreenViewActivity.this,
                utils.getFilePaths());


        viewPager.setAdapter(adapter);

        // displaying selected image first

        viewPager.setCurrentItem(position);

    }

}