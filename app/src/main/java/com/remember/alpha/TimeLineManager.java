package com.remember.alpha;

import android.os.Environment;
import android.util.Log;

import java.io.File;
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

    public List<TimeLines> gettimeLines() {
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
                Log.i("ImageLoading", timeLineName);
                timeLine.name = timeLineName;
                timeLine.members = timeLineMembers;

                timeLine.imageName = timeLineName;
                timeLines.add(timeLine);
            }

        }

        return  timeLines;
    }

}
