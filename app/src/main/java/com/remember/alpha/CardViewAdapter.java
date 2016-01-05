package com.remember.alpha;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    private List<TimeLines> timelines;
    private int rowLayout;
    private static Context mContext;

    public CardViewAdapter(List<TimeLines> timelines, int rowLayout, Context context) {
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
        TimeLines timeLine = timelines.get(i);
        viewHolder.timelineName.setText(timeLine.name);
        viewHolder.timelineMembers.setText(timeLine.members);
        if(timeLine.getImageResourceId(mContext) != -1)
            try {
                viewHolder.timelineImage.setImageResource(timeLine.getImageResourceId(mContext));
            } catch(Exception e) {

            }

    }

    @Override
    public int getItemCount() {
        return timelines == null ? 0 : timelines.size();
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
