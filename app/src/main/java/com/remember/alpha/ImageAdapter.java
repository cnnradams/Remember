package com.remember.alpha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private final List<Container> mItems = new ArrayList<Container>();
    private Context mContext;
    private final LayoutInflater mInflater;
    public ImageAdapter(Context c) {
        mInflater = LayoutInflater.from(c);
        mItems.add(new Container("A Doggie :D",R.drawable.sample_0));
        mItems.add(new Container("A Doggie :D",   R.drawable.sample_1));
        mItems.add(new Container("A Doggie :D", R.drawable.sample_1));
        mItems.add(new Container("A Doggie :D",      R.drawable.sample_0));
        mItems.add(new Container("A Doggie :D",     R.drawable.sample_1));
        mItems.add(new Container("A Doggie :D",      R.drawable.sample_0));
        mItems.add(new Container("A Doggie :D",R.drawable.sample_0));
        mItems.add(new Container("A Doggie :D",   R.drawable.sample_1));
        mItems.add(new Container("A Doggie :D", R.drawable.sample_1));
        mItems.add(new Container("A Doggie :D",      R.drawable.sample_0));
        mItems.add(new Container("A Doggie :D",     R.drawable.sample_1));
        mItems.add(new Container("A Doggie :D",      R.drawable.sample_0));
        mContext = c;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Container getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mItems.get(i).drawableId;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        SquareImageView imageView;
        TextView overlayText;
        if (convertView == null) {
            v = mInflater.inflate(R.layout.memory_item, parent, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

imageView = (SquareImageView)v.getTag(R.id.picture);
        overlayText = (TextView)v.getTag(R.id.text);
        Container item;
        item = getItem(position);
        imageView.setImageResource(item.drawableId);
        overlayText.setText(item.name);
        return v;
    }
    private static class Container {
        public final String name;
        public final int drawableId;

        Container(String name, int drawableId) {
            this.name = name;
            this.drawableId = drawableId;
        }
    }


}