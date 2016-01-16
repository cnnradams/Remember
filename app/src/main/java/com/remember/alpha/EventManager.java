package com.remember.alpha;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cnnr2 on 2016-01-05.
 */
import android.content.Context;
import android.location.Location;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    public ArrayList<Event> events = new ArrayList<Event>();
private Activity context;
    public TinyDB save;
    public EventManager(Activity c) {
        //Gets Event Objects from database
        save = new TinyDB(c);
        events = save.getListEvent("events");
        for(Event e : events) {

        }
        this.context = c;
        save.putListEvent("events",events);
    }
    // members will need to be replaced with an arraylist of user objects that we will(hopefully) make.
    public void NewEvent(String name, ArrayList<String> members) {
        GetLocation location = new GetLocation(context);

        Event newEvent = new Event(name,members,location.getLocation().getLongitude(),location.getLocation().getLatitude());

        events = save.getListEvent("events");
        events.add(newEvent);
        save.putListEvent("events",events);
    }
    public void DeleteEvent(String id) {

        events = save.getListEvent("events");

        for(Event e : events) {

if(e.id.equals(id)) {
    Log.i("EventManager","Event Deleted!");
    events.remove(e);
    save.putListEvent("events",events);
    File dir = new File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES)+ "/Remember/" + id );
    if (dir.isDirectory())
    {
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++)
        {
            new File(dir, children[i]).delete();
        }
    }

    dir.delete();
    return;
}

        }
    }

    public void DeleteMember(String memberName, String eventName) {
        events = save.getListEvent("events");
    }
    public ArrayList<Event> GetEvents() {
        events = save.getListEvent("events");
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
        //This is the local id, it is also the folder in which it is stored, we parse by this id
        public String id;
      public Double longitude;
        public Double latitude;
        public String myLocationName;
        /*    public final int drawableId;*/
        public String getName() {
            return name;
        }
        public ArrayList<String> getMembers() {
            return members;
        }

        Event(String name,ArrayList<String> members, Double longitude, double latitude/*,  int drawableId*/) {
            id = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + name;
            this.longitude = longitude;
            this.latitude = latitude;
            this.members = members;
            this.name = name;
            JsonReader jsonReader = new JsonReader();
            this.myLocationName = jsonReader.getJsonData("city","http://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon=" + longitude)
                    + ", " + jsonReader.getJsonData("state","http://nominatim.openstreetmap.org/reverse?format=json&lat=" + latitude + "&lon=" + longitude);
            /*this.drawableId = drawableId;*/
        }
    }
}
