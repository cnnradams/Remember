package com.remember.alpha;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TimeLineManager {
    private static String[] timeLineArray = {"placeholder","placeholder","placeholder","placeholder","placeholder","placeholder","placeholder","placeholder","placeholder"};
    private static String timeLineMembers = "John, Jill, and Placeholder";
    private static TimeLineManager mInstance;
    private List<TimeLines> timeLines;

    public static TimeLineManager getInstance() {
        if (mInstance == null) {
            mInstance = new TimeLineManager();
        }

        return mInstance;
    }

    public List<TimeLines> gettimeLines(Context mContext) {
        if (timeLines == null) {
            timeLines = new ArrayList<TimeLines>();

            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "Remember");

            if (! mediaStorageDir.exists()){
                if (! mediaStorageDir.mkdirs()){
                    Log.d("Remember", "failed to create directory");
                    return null;
                }
            }

            File[] images = mediaStorageDir.listFiles();

            for (File image : images) {
                TimeLines timeLine = new TimeLines();

                String timeLineName = image.getName();
                timeLine.name = timeLineName;
                timeLine.members = timeLineMembers;
                timeLine.imageName = timeLineName;
                try {
                    timeLine.image = decodeUri(mContext, timeLine.getImageUri(), 100);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                timeLines.add(timeLine);
            }

        }

        return  timeLines;
    }

    /*
    * Used to avoid java.lang.OutOfMemoryError from loading
    * images that are too large
    */
    private static Bitmap decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth
                , height_tmp = o.outHeight;
        int scale = 1;

        while(true) {
            if(width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }

}
