package com.remember.alpha;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cnnr2 on 2016-01-05.
 */
public class EventManager {
    public List<Event> events = new ArrayList<>();
    public TinyDB save;
    public EventManager(Context c) {
save = new TinyDB(c);
       
    }
    public void NewEvent(String name) {

    }
    public void DeleteEvent(String name) {

    }
    public void DeleteMember(String memberName, String eventName) {

    }
    public void AddMember(String memberName) {

    }
    public String GetEventMembers() {

    }
    public String GetEventPhoto() {

    }
    private static class Event {
        public final String name;
        public final String[] members;
        public final int drawableId;

        Event(String name,String[] members,  int drawableId) {
            this.members = members;
            this.name = name;
            this.drawableId = drawableId;
        }
    }
}
