package com.remember.alpha;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;

public class TimeLines {
    public String name;
    public String members;
    public String imageName;
    public Bitmap image;



    public Uri getImageUri()
    {
        return Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES)+ "/Remember/" + imageName));
    }


}
