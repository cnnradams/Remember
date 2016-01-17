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
        viewHolder.timelineLocation.setText(timeLine.myLocationName);
        ArrayList<Bitmap> bitmapArrayList = timeLine.SetImages(mContext);
        if(bitmapArrayList.get(0) != null)
        viewHolder.timelineImage.setImageBitmap(bitmapArrayList.get(0));
        if(bitmapArrayList.get(1) != null)
        viewHolder.timelineImage2.setImageBitmap(bitmapArrayList.get(1));
        if(bitmapArrayList.get(2) != null)
        viewHolder.timelineImage3.setImageBitmap(bitmapArrayList.get(2));

        //  viewHolder.timelineMembers.setText(timeLine.members[);
       /* if(timeLine.image != null) {
            try {
                viewHolder.timelineImage.setImageBitmap(timeLine.image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
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

            timelineName = (TextView) itemView.findViewById(R.id.timeline_name);
            timelineMembers = (TextView) itemView.findViewById(R.id.timeline_members);
            timelineLocation = (TextView) itemView.findViewById(R.id.timeline_location);
            timelineImage = (SquareImageView)itemView.findViewById(R.id.timeline_photo);
            timelineImage2 = (SquareImageView)itemView.findViewById(R.id.timeline_photo2);
            timelineImage3 = (SquareImageView)itemView.findViewById(R.id.timeline_photo3);

        }

    }

}
