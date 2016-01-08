package com.remember.alpha.adapters;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by cnnr2 on 2016-01-06.
 */
public class Memories {
    public String name;
    public String imageName;
    public Bitmap image;
public String folderPath;


    public Uri getImageUri()
    {

        return Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES)+ "/Remember/" + folderPath + "/"  + imageName));

    }

}
