package com.example.vishal.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by vishal on 4/12/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contextManager";

    // Tagss table name
    private static final String TABLE_CONTEXT = "context";

    // Tagss Table Columns names
    private static final String KEY_FNAME = "fname";
    private static final String KEY_UTIME = "utime";//update time
    private static final String KEY_TTAGS = "timetag";
    private static final String KEY_PTAGS = "placetag";
    private static final String KEY_ITAGS = "inftag";//inference tag
    private Context cont;

    private static DatabaseHandler inst;

    public static DatabaseHandler getInstance(Context context) {
        if (inst == null) {
            Log.w("Handler","First Time Running");
            inst = new DatabaseHandler(context.getApplicationContext());
        }
            Log.w("Handler","Running");
        return inst;
    }

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.w("Constructor", "Called");
        //cont=context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            //cont.deleteDatabase(DATABASE_NAME);
            //db.execSQL("delete from "+ TABLE_CONTEXT);
            String CREATE_DB_TABLE = "CREATE TABLE " + TABLE_CONTEXT + "("
                    + KEY_FNAME + " TEXT PRIMARY KEY," + KEY_UTIME + " TEXT,"
                    + KEY_TTAGS + " TEXT," + KEY_PTAGS + " TEXT," + KEY_ITAGS + " TEXT);";
            Log.w("Table Create",CREATE_DB_TABLE);
            db.execSQL(CREATE_DB_TABLE);

        }catch(Exception e){
            Log.e("Table",e.getLocalizedMessage());
        }
    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        Log.w("DROP TABLE", "Droping Table");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTEXT);

        // Create tables again
        onCreate(db);
    }
    /*
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTEXT);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    boolean recordExist (String fieldValue) {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Log.w("Query",fieldValue);
            //String Query = "Select * from " + TABLE_CONTEXT + " where " + KEY_FNAME + " = " + fieldValue;
            Cursor cursor = db.query(TABLE_CONTEXT,new String[] {KEY_FNAME}, KEY_FNAME +"=?",new String[]{fieldValue},null,null,null);
            if (cursor.getCount() <= 0) {
                cursor.close();
                return false;
            }
            cursor.close();
        }catch(Exception e){
            Log.e("RecordExist",e.getLocalizedMessage());
            onCreate(db);
        }
        return true;
    }
    // Adding new tag
    void addTags(Tags tag) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        try {
            values.put(KEY_FNAME, tag.getFname()); // Tags FName
            values.put(KEY_UTIME, tag.getUtime()); // Tags UTime
            values.put(KEY_TTAGS, tag.getTimetag()); // Tags Time
            values.put(KEY_PTAGS, tag.getPlacetag()); // Tags Time
            values.put(KEY_ITAGS, tag.getInftag()); // Tags Inf

            // Inserting Row
            if(!recordExist(tag.getFname()))
                db.insert(TABLE_CONTEXT, null, values);
            //db.close(); // Closing database connection
        }catch(Exception e){
            Log.w("AddTag",e.getLocalizedMessage());

        }
    }

    // Getting single tag
    Tags getTags(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTEXT, new String[] { KEY_FNAME,
                        KEY_UTIME, KEY_TTAGS, KEY_PTAGS, KEY_ITAGS }, KEY_FNAME + "=?",
                new String[] { id }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Tags tag = new Tags(cursor.getString(0),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return tag;
    }

    // Getting All Tagss
    public List<Tags> getAllTags() {
        List<Tags> contextList = new ArrayList<Tags>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTEXT;

        SQLiteDatabase db = this.getWritableDatabase();
        //db.query(TABLE_CONTEXT,new String[]{KEY_FNAME, KEY_UTIME,KEY_TTAGS,KEY_PTAGS,KEY_ITAGS},"*",)
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Tags contact = new Tags();
                contact.setFname(cursor.getString(0));
                contact.setUtime(cursor.getString(1));
                contact.setTimetag(cursor.getString(2));
                try {
                    contact.setPlacetag(cursor.getString(3));
                    contact.setInftag(cursor.getString(4));
                }catch(Exception e){
                    Log.w("Get", cursor.getString(0));
                }
                // Adding contact to list
                contextList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contextList;
    }

    // Updating single tag
    public int updateTags(Tags tag) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, tag.getFname());
        values.put(KEY_UTIME, tag.getUtime());

        // updating row
        return db.update(TABLE_CONTEXT, values, KEY_FNAME + " = ?",
                new String[] { String.valueOf(tag.getFname()) });
    }

    // Deleting single tag
    public void deleteTags(Tags tag) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTEXT, KEY_FNAME + " = ?",
                new String[] { String.valueOf(tag.getFname()) });
        //db.close();
    }


    // Getting Tag Count
    public int getTagsCount() {
        int count;
        String countQuery = "SELECT  * FROM " + TABLE_CONTEXT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

}
