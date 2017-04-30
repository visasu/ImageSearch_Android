package com.example.vishal.myapplication;

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
=======
import static android.R.attr.id;

/**
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
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
