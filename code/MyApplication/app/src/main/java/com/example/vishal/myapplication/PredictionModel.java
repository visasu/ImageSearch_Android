package com.example.vishal.myapplication;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

<<<<<<< HEAD
import java.io.ByteArrayOutputStream;

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
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
 * Created by vishal on 4/26/17.
 */

public class PredictionModel {
    private static final String MODEL_FILE = "file:///android_asset/optimized_facenet.pb";
<<<<<<< HEAD
=======
    //private static final String MODEL_FILE = "file:///android_asset/frozen_tfdroid.pb";
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
    private String FILE_PATH = "file://android_asset/mnist_test.csv";
    private static final String INPUT_NODE = "x";
    private static final String DROPOUT_NODE = "drop";
    private static final String OUTPUT_NODE = "out";
<<<<<<< HEAD
=======
    //private static final int[] INPUT_SIZE = {1,3};
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
    private static final int[] INPUT_SIZE = {1,64*64};
    private static final int VECTOR_SIZE = 4096; //64*64;
    private static final int ROW = 64;
    private static final int COL = 64;
    private static final int CLASS_SIZE = 9;
    private static final String[] classes={"Ariel_Sharon", "Arnold_Schwarzenegger", "Colin_Powell", "Donald_Rumsfeld", "George_W_Bush", "Gerhard_Schroeder", "Hugo_Chavez", "Jacques_Chirac", "Jean_Chretien"};
    private static AssetManager location;
<<<<<<< HEAD
=======
    InputStream in;
    BufferedReader reader;
    String line;
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
    int index=0;

    private TensorFlowInferenceInterface inferenceInterface;

    static {
        System.loadLibrary("tensorflow_inference");
    }
    PredictionModel(String filepath,AssetManager loc ){
        FILE_PATH=filepath;
        location=loc;

    }
    public String getPrediction(){
<<<<<<< HEAD
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
=======
        /*
        try {
            in = location.open(FILE_PATH);
            reader = new BufferedReader((new InputStreamReader(in)));
        }catch (Exception e){
            Log.e("Location error", e.getLocalizedMessage());
        }
        */
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        //Bitmap bitmap = BitmapFactory.decodeFile(_imagePaths.get(position), options);
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1

        Bitmap bmp = BitmapFactory.decodeFile(FILE_PATH, options);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        System.out.println("ARRAY="+Integer.toString(byteArray.length));
        System.out.println("ARRAYSTR="+Integer.toString(bmp.getWidth()));

        byte[] image = byteArray;
        float prob=0;
        if(image==null){
            Log.e("FILEREADER_RET_FAILED",image.toString());
        }
        try {
            if(location==null){
                Log.e("ASSET_MANAGER","CHECK THE FAILURE");
                throw null;
            }
            inferenceInterface = new TensorFlowInferenceInterface();
            inferenceInterface.initializeTensorFlow(location, MODEL_FILE);
        }catch(Exception e){
            Log.w("INFERENCE_FAILED", e.getLocalizedMessage());
        }
        float[] val = new float[4096];
        if(val==null){
            Log.e("Problem with float","Check declaration");
        }
        Log.w("VAL Length", Integer.toString(val.length));
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++){
                try {
                    val[i * COL + j] = Float.parseFloat(Integer.toString(bmp.getPixel(i,j)));
                }catch(Exception e){
                    Log.e("OUTOFBOUND",e.getLocalizedMessage());
                }
            }
        }
        try {
<<<<<<< HEAD
=======
            //inferenceInterface.fillNodeInt(INPUT_NODE, INPUT_SIZE, val);
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
            inferenceInterface.fillNodeFloat(INPUT_NODE, INPUT_SIZE, val);
            inferenceInterface.fillNodeFloat(DROPOUT_NODE, new int[]{1}, new float[]{0.5f});

            inferenceInterface.runInference(new String[]{OUTPUT_NODE});

<<<<<<< HEAD
=======
            //float[] resu = {0, 0};
>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
            float[] resu = new float[CLASS_SIZE];
            inferenceInterface.readNodeFloat(OUTPUT_NODE, resu);
            float max = resu[0];
            index=0;
            float sum= resu[0];
            for(int i=1;i<CLASS_SIZE;i++){
                if(max<resu[i]){
                    index=i;
                    max=resu[i];
                }
                sum = sum + resu[i];
            }
            prob = max/sum;

        } catch (Exception e) {
            Log.e("FileNotFound", e.getLocalizedMessage());
        }
        Log.w("Actual Result",FILE_PATH);
        Log.w("Inference Result",classes[index]);
        Log.w("Probability Result",Float.toString(prob));
        return classes[index];

    }
<<<<<<< HEAD
=======
    public static byte[][] filereader(String filepath) {
        byte[][] im={};
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
            im = new byte[height][width];

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
                            im[count / width][count % width] = (byte)((b >> 8) & 0xFF);
                            count++;
                            im[count / width][count % width] = (byte)(b & 0xFF);
                            count++;
                        }
                        else {  // ASCII format
                            im[count / width][count % width] = (byte)b ;
                            count++;
                        }
                    }
                }
                Log.d("FILE_READER_Succ","Completed"+filepath);
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


>>>>>>> e946ccf91f72080f85bd4d6cf1c4a6b87489e6c1
}
