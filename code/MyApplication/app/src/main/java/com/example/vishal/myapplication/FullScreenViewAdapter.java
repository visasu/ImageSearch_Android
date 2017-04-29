package com.example.vishal.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by vishal on 4/12/17.
 */

public class FullScreenViewAdapter extends PagerAdapter {

    private Activity _activity;
    private ArrayList<String> _imagePaths;
    private LayoutInflater inflater;
    private DatabaseHandler db;

    // constructor
    public FullScreenViewAdapter(Activity activity,
                                  ArrayList<String> imagePaths) {
        this._activity = activity;
        this._imagePaths = imagePaths;
    }

    @Override
    public int getCount() {
        return this._imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgDisplay;
        Button btnClose;
        TextView f_name, p_tag, t_tag, i_tag;
        String filename;
        List<Tags> allTags;

        db = DatabaseHandler.getInstance(_activity.getBaseContext());

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
                false);

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);
        btnClose = (Button) viewLayout.findViewById(R.id.btnClose);
        f_name = (TextView) viewLayout.findViewById(R.id.f_name);
        t_tag = (TextView) viewLayout.findViewById(R.id.t_tag);
        p_tag = (TextView) viewLayout.findViewById(R.id.p_tag);
        i_tag = (TextView) viewLayout.findViewById(R.id.i_tag);

        allTags= db.getAllTags();
        filename = allTags.get(position).getFname().split("/", 7)[6];


        //filename = _imagePaths.get(position).split("/", 7)[6];
        Log.d("Pic",filename);

        f_name.setText(filename);
        t_tag.setText(allTags.get(position).getTimetag());
        p_tag.setText(allTags.get(position).getPlacetag());
        i_tag.setText(allTags.get(position).getInftag());

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(_imagePaths.get(position), options);
        imgDisplay.setImageBitmap(bitmap);

        // close button click event
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _activity.finish();
            }
        });

        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }
    public static byte[] filereader(String filepath) {
        byte[] im={};
        try {
            InputStream f = ClassLoader.getSystemClassLoader().getResourceAsStream(filepath);
            //InputStream f = location.open(filepath);
            BufferedReader d = new BufferedReader(new InputStreamReader(f));
            String magic = d.readLine();    // first line contains P2 or P5
            String line = d.readLine();     // second line contains height and width
            while (line.startsWith("#")) {
                line = d.readLine();
            }
            Scanner s = new Scanner(line);
            int width = s.nextInt();
            int height = s.nextInt();
            line = d.readLine();// third line contains maxVal
            s = new Scanner(line);
            int maxVal = s.nextInt();
            im = new byte[height*width];

            int count = 0;
            int b = 0;
            try {
                while (count < height*width) {
                    b = d.read() ;
                    if ( b < 0 )
                        break ;

                    if (b == '\n') { // do nothing if new line encountered
                    }
//                  else if (b == '#') {
//                      d.readLine();
//                  }
//                  else if (Character.isWhitespace(b)) { // do nothing if whitespace encountered
//                  }
                    else {
                        if ( "P5".equals(magic) ) { // Binary format
                            im[count] = (byte)((b >> 8) & 0xFF);
                            count++;
                            im[count] = (byte)(b & 0xFF);
                            count++;
                        }
                        else {  // ASCII format
                            im[count] = (byte)b ;
                            count++;
                        }
                    }
                }
                Log.w("FullScreenFILE_READER","Completed"+filepath);
            } catch (EOFException eof) {
                eof.printStackTrace(System.out) ;
                Log.e("EOF_FOUND", "CHECK");
            }
            System.out.println("Height=" + height);
            System.out.println("Width=" + height);
            System.out.println("Required elements=" + (height * width));
            System.out.println("Obtained elements=" + count);
        }
        catch(Exception e) {
            Log.e("File_reader_FAILED",e.getLocalizedMessage());
        }
        return im;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

}
