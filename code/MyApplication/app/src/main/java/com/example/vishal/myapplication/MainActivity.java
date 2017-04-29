package com.example.vishal.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
//        setContentView(R.layout.activity_grid_view);

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