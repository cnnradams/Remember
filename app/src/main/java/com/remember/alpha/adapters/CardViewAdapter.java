package com.remember.alpha.adapters;

import android.content.Context;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.remember.alpha.EventManager;
import com.remember.alpha.JsonReader;
import com.remember.alpha.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    private ArrayList<EventManager.Event> timelines;
    private int rowLayout;
    private static Context mContext;

    public CardViewAdapter(ArrayList<EventManager.Event> timelines, int rowLayout, Context context) {
        this.timelines = timelines;
        this.rowLayout = rowLayout;
        this.mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        EventManager.Event timeLine = timelines.get(i);
        viewHolder.timelineName.setText(timeLine.name);
        JsonReader jsonReader = new JsonReader();

        if(timeLine.latitude != null) {
            try {
               String locationString =  jsonReader.getJsonData("city","http://nominatim.openstreetmap.org/reverse?format=json&lat=" + timeLine.latitude + "&lon=" + timeLine.longitude)
                       + ", " + jsonReader.getJsonData("state","http://nominatim.openstreetmap.org/reverse?format=json&lat=" + timeLine.latitude + "&lon=" + timeLine.longitude);
                viewHolder.timelineLocation.setText(locationString);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }


        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Remember/" + timeLine.id);
        File[] images = mediaStorageDir.listFiles();
        if (images != null) {
            try {
                int image1 =(int) (Math.random() * (images.length));
                int image2 = (int) (Math.random() * (images.length));
                int image3 = (int) (Math.random() * (images.length));
                if(images.length > 2) {

                    while(image2 == image1 || image2 == image3) {
                        image2 = (int) (Math.random() * (images.length));
                    }
                    while(image3 == image2 || image3 == image1) {
                        image3 = (int) (Math.random() * (images.length));
                    }
                }

                if(images.length > 0) {
                    viewHolder.timelineImage.setImageBitmap(decodeUri(mContext, Uri.fromFile( images[image1]), 100));
                    viewHolder.timelineImage2.setImageBitmap(decodeUri(mContext, Uri.fromFile(images[image2]), 100));
                    viewHolder.timelineImage3.setImageBitmap(decodeUri(mContext, Uri.fromFile(images[image3]), 100));
                }
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    public int getItemCount() {
        return timelines == null ? 0 : timelines.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView timelineName;
        public TextView timelineMembers;
        public TextView timelineLocation;
       public SquareImageView timelineImage;
        public SquareImageView timelineImage2;
        public SquareImageView timelineImage3;
        public String id;


        public ViewHolder(View itemView) {
            super(itemView);
          // this.id = id;
            timelineName = (TextView) itemView.findViewById(R.id.timeline_name);
            timelineMembers = (TextView) itemView.findViewById(R.id.timeline_members);
            timelineLocation = (TextView) itemView.findViewById(R.id.timeline_location);
            timelineImage = (SquareImageView)itemView.findViewById(R.id.timeline_photo);
            timelineImage2 = (SquareImageView)itemView.findViewById(R.id.timeline_photo2);
            timelineImage3 = (SquareImageView)itemView.findViewById(R.id.timeline_photo3);

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
