package com.remember.alpha;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class TimeLines {
    public String name;
    public String members;
    public String imageName;


    public Uri getImageUri()
    {
        Log.i("Loading Image", Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) + "/Remember/" + imageName);

        return Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES)+ "/Remember/" + imageName));
    }
}
