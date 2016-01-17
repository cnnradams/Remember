package com.remember.alpha;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.remember.alpha.adapters.Memories;
import com.remember.alpha.adapters.MemoriesManager;

import java.util.List;

/**
 * Created by Christopher on 11/01/2016.
 * Used with ImageDetailActivity to show images
 * tapped on in the gridview.
 */
public class ImageDetailFragment extends Fragment {
    private static final String IMAGE_DATA_EXTRA = "imgId";
    private static final String EVENT_ID_EXTRA = "eventId";
    private int mImageNum;
    private String eventID;
    private ImageView mImageView;

    static ImageDetailFragment newInstance(int imageNum, String eventID) {
        final ImageDetailFragment f = new ImageDetailFragment();
        final Bundle args = new Bundle();
        args.putInt(IMAGE_DATA_EXTRA, imageNum);
        args.putString(EVENT_ID_EXTRA, eventID);
        f.setArguments(args);
        return f;
    }

    // Empty constructor, required as per Fragment docs
    public ImageDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageNum = getArguments() != null ? getArguments().getInt(IMAGE_DATA_EXTRA) : -1;
        eventID = getArguments() != null ? getArguments().getString(EVENT_ID_EXTRA) : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // image_detail_fragment.xml contains just an ImageView
        final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
        mImageView = (ImageView) v.findViewById(R.id.imageViewForPager);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Memories memories = MemoriesManager.getInstance(eventID).getMemories(getContext(), eventID).get(mImageNum);
/*
        List<Memories> imageBitmaps = MemoriesManager.getInstance(eventID).getMemories(getContext(), eventID);
        final Bitmap img = imageBitmaps.get(mImageNum).highResImage;
        mImageView.setImageBitmap(img);*/ // Load image into ImageView
        //MemoriesManager.getInstance(eventID).setImageViewToMemory(getContext(), eventID, mImageView, mImageNum);
        MemoriesManager.getInstance(memories.folderPath).loadMemoryImage(getContext(), memories.folderPath,mImageNum,mImageView);
    }


}
