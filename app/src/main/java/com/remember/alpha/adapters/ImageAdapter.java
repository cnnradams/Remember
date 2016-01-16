package com.remember.alpha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.remember.alpha.R;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private List<Memories> mItems = new ArrayList<>();
    private Context mContext;

    private int rowLayout;
    private final LayoutInflater mInflater;
    public ImageAdapter(List<Memories> timelines, int rowLayout,Context c) {

        this.mItems = timelines;
        this.rowLayout = rowLayout;
        mInflater = LayoutInflater.from(c);


        mContext = c;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Memories getItem(int i) {
        return mItems.get(i);
    }
//Not using this but is has to be used
    @Override
    public long getItemId(int i) {
       return i;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        SquareImageView imageView;
        TextView overlayText;
        if (convertView == null) {
            v = mInflater.inflate(rowLayout, parent, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
           // v.setTag(R.id.text, v.findViewById(R.id.text));
        }

imageView = (SquareImageView)v.getTag(R.id.picture);
       // overlayText = (TextView)v.getTag(R.id.text);
        Memories memories = getItem(position);

        /*if(memories.highResImage != null) {
            try {
                imageView.setImageBitmap(memories.highResImage);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }*/

        MemoriesManager.getInstance(memories.folderPath).loadMemoryIconImage(mContext, memories.folderPath,position,imageView);

       // overlayText.setText(memories.name);
        return v;
    }



}