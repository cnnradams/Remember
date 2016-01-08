package com.remember.alpha;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cnnr2 on 2016-01-05.
 */
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    public ArrayList<Event> events = new ArrayList<Event>();

    public TinyDB save;
    public EventManager(Context c) {
        save = new TinyDB(c);
        events = save.getListEvent("events");
        for(Event e : events) {
            Log.i("EventManager", e.name);
        }
        save.putListEvent("events",events);
    }
    // members will need to be replaced with an arraylist of user objects that we will(hopefully) make.
    public void NewEvent(String name, ArrayList<String> members) {
        Event newEvent = new Event(name,members);

        events = save.getListEvent("events");
        events.add(newEvent);
        save.putListEvent("events",events);
    }
    public void DeleteEvent(String name) {

        events = save.getListEvent("events");

        for(Event e : events) {
if(e.name.equals(name)) {
    events.remove(e);
}

        }
    }
    public void DeleteMember(String memberName, String eventName) {
        events = save.getListEvent("events");
    }
    public ArrayList<Event> GetEvents() {
        return events;
    }
    public void AddMember(String memberName) {
        events = save.getListEvent("events");
    }
    public String GetEventMembers() {
        return null;
    }
    public String GetEventPhoto() {
        return null;
    }
    public class Event {
        public String name;
        public ArrayList<String> members;
        public String id = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + name;
        /*    public final int drawableId;*/
        public String getName() {
            return name;
        }
        public ArrayList<String> getMembers() {
            return members;
        }

        Event(String name,ArrayList<String> members/*,  int drawableId*/) {
            this.members = members;
            this.name = name;
            /*this.drawableId = drawableId;*/
        }
    }
}
