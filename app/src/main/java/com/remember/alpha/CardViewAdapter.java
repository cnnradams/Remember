package com.remember.alpha;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.List;


public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    private List<TimeLines> countries;
    private int rowLayout;
    private Context mContext;

    public CardViewAdapter(List<TimeLines> countries, int rowLayout, Context context) {
        this.countries = countries;
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
        TimeLines timeLine = countries.get(i);
        viewHolder.timelineName.setText(timeLine.name);
        viewHolder.timelineMembers.setText(timeLine.members);
        if(timeLine.image != null) {
            try {
                viewHolder.timelineImage.setImageBitmap(timeLine.image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView timelineName;
        public TextView timelineMembers;
        public ImageView timelineImage;


        public ViewHolder(View itemView) {
            super(itemView);
            timelineName = (TextView) itemView.findViewById(R.id.timeline_name);
            timelineMembers = (TextView) itemView.findViewById(R.id.timeline_members);
            timelineImage = (ImageView)itemView.findViewById(R.id.timeline_photo);
        }

    }
}