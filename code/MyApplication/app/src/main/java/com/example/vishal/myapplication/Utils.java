package com.example.vishal.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by vishal on 4/1/17.
 */
public class Utils {

    private Context _context;
    private DatabaseHandler db;
    private AssetManager asset;

    // constructor
    public Utils(Context context, AssetManager asset) {
        this._context = context;
        this.asset=asset;
        initdb();

    }

    // Reading file paths from SDCard
    public ArrayList<String> getFilePaths() {
        ArrayList<String> filePaths = new ArrayList<String>();

        File directory = new File(
                android.os.Environment.getExternalStorageDirectory()
                        + File.separator + AppConstant.PHOTO_ALBUM);
//        File directory = new File(AppConstant.PHOTO_ALBUM);

        // check for directory
        if (directory.isDirectory()) {
            // getting list of file paths
            Log.d("Dir",directory.toString());
            File[] listFiles = directory.listFiles();
/*            if (ContextCompat.checkSelfPermission(_context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.d("Dir", "Inside dir");
            }
            else{
                ActivityCompat.requestPermissions(, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 12321);
            }
*/            // Check for count
            if (listFiles.length > 0){

                // loop through all files
                for (int i = 0; i < listFiles.length; i++) {

                    // get file path
                    String filePath = listFiles[i].getAbsolutePath();

                    // check for supported file extension
                    if (IsSupportedFile(filePath)) {
                        // Add image path to array list
                        filePaths.add(filePath);
                    }
                }
            } else {
                // image directory is empty
                Toast.makeText(
                        _context,
                        AppConstant.PHOTO_ALBUM
                                + " is empty. Please load some images in it !",
                        Toast.LENGTH_LONG).show();
            }

        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(_context);
            alert.setTitle("Error!");
            alert.setMessage(AppConstant.PHOTO_ALBUM
                    + " directory path is not valid! Please set the image directory name AppConstant.java class");
            alert.setPositiveButton("OK", null);
            alert.show();
        }

        return filePaths;
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


    public ArrayList<String> findTag(String search) {
        List<Tags> allTags;
        ArrayList<String> hits = new ArrayList<String>();
        String[] sWords = search.split("\\s+");
        ArrayList<String> searchWords = new ArrayList<String>();
        HashMap<String,Integer> map =new HashMap<String, Integer>();
        // Create an array of searchWords
        HashMap<String, Boolean> found =new HashMap<>();

        for (String word:sWords) {
            //Add unique occurence code
        //    if(!found.containsKey(word)) {
                searchWords.add(word);
        //        map.put(word, 1);
        //    }
        }
        db=DatabaseHandler.getInstance(_context);
        allTags = db.getAllTags();
        if(searchWords.isEmpty()) {
            for (Tags a: allTags) {
                hits.add(a.getFname());
            }
            return hits;
        }
//        ArrayList<String> searchTag = new ArrayList<String>(){};
        //Scan through AllTags
        for(String sTag : searchWords){
            for(Tags a :allTags){
                String infTag = a.getFname();
                try {
                    if(infTag.contains(sTag)) {
                        if (map.containsKey(infTag)) {
                            map.put(infTag, map.get(infTag) + 1);
                            Log.w("findtag Found",infTag);
                        } else {
                            map.put(infTag, 1);
                            Log.w("findtag Found",infTag);
                        }
                    }


                    infTag = a.getPlacetag();
                    if (infTag.contains(sTag)) {
                        if (map.containsKey(a.getFname())) {
                            map.put(a.getFname(), map.get(a.getFname()) + 1);
                            Log.w("findtag Found", a.getFname());
                        } else {
                            map.put(a.getFname(), 1);
                            Log.w("findtag Found", a.getFname());
                        }
                    }

                    infTag = a.getInftag();
                    if (infTag.contains(sTag)) {
                        if (map.containsKey(a.getFname())) {
                            map.put(a.getFname(), map.get(a.getFname()) + 1);
                            Log.w("findtag Found", a.getFname());
                        } else {
                            map.put(a.getFname(), 1);
                            Log.w("findtag Found", a.getFname());
                        }
                    }

                }catch (Exception e){
                    Log.d("Exception", e.getLocalizedMessage());
                }
            }

        }

        ValueComparator<String, Integer> comparator = new ValueComparator<String, Integer> (map);
        Map<String, Integer> sortedMap = this.sortByValue(map);

        hits = new ArrayList<String> (sortedMap.keySet());

        return hits;
    }



    public void initdb() {
        ArrayList<String> timeStamps = new ArrayList<String>();
        String place = "Tempe";
        File directory = new File(
                android.os.Environment.getExternalStorageDirectory()
                        + File.separator + AppConstant.PHOTO_ALBUM);
//        File directory = new File(AppConstant.PHOTO_ALBUM);
        db = DatabaseHandler.getInstance(_context);

        // check for directory
        if (directory.isDirectory()) {
            // getting list of file paths
            Log.d("Dir",directory.toString());
            File[] listFiles = directory.listFiles();
/*            if (ContextCompat.checkSelfPermission(_context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.d("Dir", "Inside dir");
            }
            else{
                ActivityCompat.requestPermissions(, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 12321);
            }
*/            // Check for count
            if (listFiles.length > 0){

                // loop through all files
                for (int i = 0; i < listFiles.length; i++) {

                    // get file path
                    String filePath = listFiles[i].getAbsolutePath();

                    Date TimeStamp = new Date(listFiles[i].lastModified());

                    // check for supported file extension
                    if (IsSupportedFile(filePath)) {
                        // Add image path to array list
                        PredictionModel predict= new PredictionModel(filePath,asset);
                        //Add Prediction Model
                        Tags tg =new Tags(filePath,TimeStamp.toString(),TimeStamp.toString(),place,predict.getPrediction());
                        db.addTags(tg);
                        timeStamps.add(TimeStamp.toString());
                    }

                }
                Log.w("DatabaseCount",String.valueOf(db.getTagsCount()));

            } else {
                // image directory is empty
                Toast.makeText(
                        _context,
                        AppConstant.PHOTO_ALBUM
                                + " is empty. Please load some images in it !",
                        Toast.LENGTH_LONG).show();
            }

        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(_context);
            alert.setTitle("Error!");
            alert.setMessage(AppConstant.PHOTO_ALBUM
                    + " directory path is not valid! Please set the image directory name AppConstant.java class");
            alert.setPositiveButton("OK", null);
            alert.show();
        }

    }

    // Check supported file extensions
    private boolean IsSupportedFile(String filePath) {
        String ext = filePath.substring((filePath.lastIndexOf(".") + 1),
                filePath.length());

        if (AppConstant.FILE_EXTN
                .contains(ext.toLowerCase(Locale.getDefault())))
            return true;
        else
            return false;

    }

    /*
     * getting screen width
     */
    public int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) _context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }
}