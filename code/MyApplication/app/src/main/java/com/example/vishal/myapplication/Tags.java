package com.example.vishal.myapplication;

import static android.R.attr.id;

/**
 * Created by vishal on 4/12/17.
 */

public class Tags {
    //private variables
    String _fname;
    String _utime;
    String _timetag;
    String _placetag;
    String _inftag;

    // Empty constructor
    public Tags(){

    }
    // constructor
    public Tags(String fname, String utime, String ttags, String ptags, String itags){
        this._fname = fname;
        this._utime = utime;
        this._timetag = ttags;
        this._placetag = ptags;
        this._inftag = itags;

    }

    // constructor
    public Tags(String fname, String utime, String ttags, String ptags){
        this._fname = fname;
        this._utime = utime;
        this._timetag = ttags;
        this._placetag = ptags;
    }
    // getting FNAME
    public String getFname(){
        return this._fname;
    }

    // setting FNAME
    public void setFname(String fname){
        this._fname = fname;
    }

    // getting UTIME
    public String getUtime(){
        return this._utime;
    }

    // setting UTIME
    public void setUtime(String utime){
        this._utime = utime;
    }

    // getting TTag
    public String getTimetag(){
        return this._timetag;
    }

    // setting Ttag
    public void setTimetag(String ttags){
        this._timetag = ttags;
    }

    public String getPlacetag(){
        return this._placetag;
    }

    // setting place tags
    public void setPlacetag(String ptags){
        this._placetag = ptags;
    }

    public String getInftag(){
        return this._inftag;
    }

    // setting inf tag
    public void setInftag(String itags){
        this._inftag = itags;
    }

}
