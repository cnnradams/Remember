package com.remember.alpha;

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

            for (String timeLineName : timeLineArray) {
                TimeLines timeLine = new TimeLines();
                timeLine.name = timeLineName;
timeLine.members = timeLineMembers;

                timeLine.imageName = timeLineName.replaceAll("\\s+","").toLowerCase();
                timeLines.add(timeLine);
            }
        }

        return  timeLines;
    }

}
