package com.remember.alpha;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cnnr2 on 2016-01-05.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
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
        Log.e("here", "stayin alive");
        for (Event e : events) {

        }
        this.context = c;
        save.putListEvent("events", events);
    }

    // members will need to be replaced with an arraylist of user objects that we will(hopefully) make.
    public void NewEvent(String name, ArrayList<String> members) {
        GetLocation location = new GetLocation(context);

        Event newEvent = new Event(name, members, location.getLocation().getLongitude(), location.getLocation().getLatitude());

        events = save.getListEvent("events");
        events.add(newEvent);
        save.putListEvent("events", events);
    }

    public void DeleteEvent(String id) {

        events = save.getListEvent("events");

        for (Event e : events) {

            if (e.id.equals(id)) {
                Log.i("EventManager", "Event Deleted!");
                events.remove(e);
                save.putListEvent("events", events);
                File dir = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES) + "/Remember/" + id);
                if (dir.isDirectory()) {
                    String[] children = dir.list();
                    for (int i = 0; i < children.length; i++) {
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
        public Bitmap imageBitmap1;
        public Bitmap imageBitmap2;
        public Bitmap imageBitmap3;
        private int image1;
        private int image2;
        private int image3;
        /*    public final int drawableId;*/
        public String getName() {
            return name;
        }

        public ArrayList<Bitmap> SetImages(Context newContext) {
            ArrayList<Bitmap> photos = new ArrayList<>();


            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "Remember/" + id);
            File[] images = mediaStorageDir.listFiles();

            if (images != null) {

                if (image1 == image2 || image1 == image3 || image2 == image3 || imageBitmap1 == null || imageBitmap2 == null || imageBitmap3 == null) {
                    Log.e("imagesssss", images.length + "");
                    try {
                        Log.e("eventmanager", "here");
                         image1 = (int) (Math.random() * (images.length));
                         image2 = (int) (Math.random() * (images.length));
                         image3 = (int) (Math.random() * (images.length));
                        if (images.length > 2) {

                            while (image2 == image1 || image2 == image3) {
                                image2 = (int) (Math.random() * (images.length));
                            }
                            while (image3 == image2 || image3 == image1) {
                                image3 = (int) (Math.random() * (images.length));
                            }
                        }

                        if (images.length > 0) {
                            Log.e("saved", "saved");
                            imageBitmap1 = decodeUri(newContext, Uri.fromFile(images[image1]), 100);
                            imageBitmap2 = decodeUri(newContext, Uri.fromFile(images[image2]), 100);
                            imageBitmap3 = decodeUri(newContext, Uri.fromFile(images[image3]), 100);

                            Log.e("saved", "saved2");
                        }
                    } catch (java.io.FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
            photos.add(imageBitmap1);
            photos.add(imageBitmap2);
            photos.add(imageBitmap3);

return photos;
        }


        Event(String name,ArrayList<String> members, Double longitude, double latitude) {
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
