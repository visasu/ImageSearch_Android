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
 */
=======
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
package com.example.vishal.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v4.app.ActivityCompat;
<<<<<<< HEAD
=======
import android.support.v4.content.ContextCompat;
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Utils utils;
    private ArrayList<String> imagePaths = new ArrayList<String>();
    private GridViewImageAdapter adapter;
    private GridView gridView;
    private SearchView searchView;
    private int columnWidth;
    private Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
=======
//        setContentView(R.layout.activity_grid_view);
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1

        this.gridView = (GridView) findViewById(R.id.grid_view);

        this.searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setQueryHint("Add Context");

        if (getApplicationContext().checkCallingOrSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == PackageManager.PERMISSION_GRANTED) {
               Log.d("Dir", "Inside dir");
        }
        else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 12321);

        }
        utils = new Utils(this, this.getAssets());

        // Initilizing Grid View
        InitilizeGridLayout();


        // loading all image paths from SD card
        imagePaths = utils.getFilePaths();
<<<<<<< HEAD

=======
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
        // Gridview adapter
        adapter = new GridViewImageAdapter(MainActivity.this, imagePaths,
                columnWidth);

        // setting grid view adapter
        gridView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getBaseContext(), query,
                        Toast.LENGTH_SHORT).show();
                imagePaths=utils.findTag(query);
                // Gridview adapter
                adapter = new GridViewImageAdapter(MainActivity.this, imagePaths,
                        columnWidth);

                // setting grid view adapter
                gridView.setAdapter(adapter);
                return false;

            }
            @Override
            public boolean onQueryTextChange(String query) {
                imagePaths=utils.findTag(query);
                // Gridview adapter
                adapter = new GridViewImageAdapter(MainActivity.this, imagePaths,
                        columnWidth);

                // setting grid view adapter
                gridView.setAdapter(adapter);
                return true;
            }

        });

    }

    private void InitilizeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                AppConstant.GRID_PADDING, r.getDisplayMetrics());

        columnWidth = (int) ((utils.getScreenWidth() - ((AppConstant.NUM_OF_COLUMNS + 1) * padding)) / AppConstant.NUM_OF_COLUMNS);

        gridView.setNumColumns(AppConstant.NUM_OF_COLUMNS);
        gridView.setColumnWidth(columnWidth);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        gridView.setHorizontalSpacing((int) padding);
        gridView.setVerticalSpacing((int) padding);
    }

}