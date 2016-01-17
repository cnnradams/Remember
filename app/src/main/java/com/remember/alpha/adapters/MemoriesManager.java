package com.remember.alpha.adapters;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cnnr2 on 2016-01-06.
 */
public class MemoriesManager {
    private static String[] memoryArray = {"placeholder","placeholder","placeholder","placeholder","placeholder","placeholder","placeholder","placeholder","placeholder"};

    private List<Memories> memoriesList;
private int photosCount;
    public static ArrayList<Instances> currentInstances = new ArrayList<>();
    private File[] firstTimeImages;
    public static MemoriesManager getInstance(String eventId) {
        for(Instances instance : currentInstances) {
            if(instance.id.equals(eventId)) {
                return instance.thisInstance;
            }
        }
        Instances newInstance = new Instances(eventId);
        currentInstances.add(newInstance);
        return newInstance.thisInstance;
    }
public static class Instances {
    public MemoriesManager thisInstance = new MemoriesManager();
    public String id;
        public Instances(String id) {
            this.id = id;
        }
    }

    /*
     * getMemories() will no longer
     * load the images.
     */
    public List<Memories> getMemories(Context mContext, String folderPath) {
        if (memoriesList == null) {
            memoriesList = new ArrayList<Memories>();

            File mediaRootStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "Remember");

            if (! mediaRootStorageDir.exists()){
                if (! mediaRootStorageDir.mkdirs()){
                    Log.d("Remember", "failed to create directory");
                    return null;
                }
            }
File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
        Environment.DIRECTORY_PICTURES), "Remember/" + folderPath);
            if (! mediaStorageDir.exists()){
                if (! mediaStorageDir.mkdirs()){
                    Log.d("Remember", "failed to create directory");
                    return null;
                }
            }
            firstTimeImages = mediaStorageDir.listFiles();
photosCount = firstTimeImages.length;
            for (File image : firstTimeImages) {
                Memories memory = new Memories();

                String memoryName = image.getName();
                memory.name = memoryName;
                memory.folderPath = folderPath;
                memory.imageName = memoryName;

                /*Uri imageUri = memory.getImageUri();

                try {
                    Bitmap highResImage = decodeUri(mContext, imageUri, 500);
                    Bitmap iconImage = decodeUri(mContext, imageUri, 50);

                    memory.iconImage = iconImage;
                    memory.highResImage = highResImage;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                memoriesList.add(memory);
            }

        } else {
         //   photosCount = firstTimeImages.length;

            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "Remember/" + folderPath);
            File[] images = mediaStorageDir.listFiles();

            if(images.length > photosCount) {

                for(int i = 0; i < (images.length - photosCount); i++) {
                    ArrayList<File> myImages = new ArrayList<>(Arrays.asList(images));

                    File image = myImages.get(images.length - i - 1);
                    Memories memory = new Memories();

                    String memoryName = image.getName();
                    memory.name = memoryName;
                    memory.folderPath = folderPath;
                    memory.imageName = memoryName;

                    /*Uri imageUri = memory.getImageUri();

                    try {
                        Bitmap highResImage = decodeUri(mContext, imageUri, 500);
                        Bitmap iconImage = decodeUri(mContext, imageUri, 50);

                        memory.iconImage = iconImage;
                        memory.highResImage = highResImage;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/

                    memoriesList.add(memory);
                }
                photosCount = images.length;
            }
        }

        return memoriesList;
    }


    public void loadMemoryIconImage(final Context mContext,final String folderPath, final int imageIndex, final SquareImageView imgView){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                Memories memory = getMemories(mContext,folderPath).get(imageIndex);

                if(memory.iconImage == null){
                    Bitmap bm = null;
                    try {
                        bm = decodeUri(mContext, memory.getImageUri(), 50);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    memoriesList.get(imageIndex).iconImage = bm;
                }

                memory = memoriesList.get(imageIndex);

                final Memories finalMemory = memory;
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    public void run() {
                        imgView.setImageBitmap(finalMemory.iconImage);
                    }
                });

            }
        }); thread.start();
    }

    public void loadMemoryImage(final Context mContext,final String folderPath, final int imageIndex, final ImageView imgView){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                Memories memory = getMemories(mContext,folderPath).get(imageIndex);

                if(memory.highResImage == null){
                    Bitmap bm = null;
                    try {
                        bm = decodeUri(mContext, memory.getImageUri(), 500);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    memoriesList.get(imageIndex).highResImage = bm;
                }

                memory = memoriesList.get(imageIndex);

                final Memories finalMemory = memory;
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    public void run() {
                        imgView.setImageBitmap(finalMemory.highResImage);
                    }
                });

            }
        }); thread.start();
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
