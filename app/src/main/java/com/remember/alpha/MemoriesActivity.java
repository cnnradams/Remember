package com.remember.alpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.remember.alpha.adapters.ImageAdapter;
import com.remember.alpha.adapters.MemoriesManager;

import java.util.ArrayList;

public class MemoriesActivity extends AppCompatActivity {
    ArrayList<EventManager.Event> events;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memories_activity);
        Intent intent = getIntent();
        extras = intent.getExtras();
        GridView memoriesGrid = (GridView) findViewById(R.id.memories_grid);
        //Setting  the Memories into a grid look using ImageAdapter
        events = new EventManager(this).GetEvents();

        String id = events.get(extras.getInt("position", -1)).id;

        memoriesGrid.setAdapter(new ImageAdapter(MemoriesManager.getInstance(id).getMemories(this, id), R.layout.memory_item, this));
        memoriesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(MemoriesActivity.this, ImageDetailActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("id", events.get(extras.getInt("position", -1)).id);
                startActivity(intent);
            }
        });
    }
public void takePicture(View view ) {
    TakePicture takethePicture = new TakePicture("Photo",events.get(extras.getInt("position", -1)).id ,MemoriesActivity.this);
    takethePicture.capturePicture();
}
}
