package com.remember.alpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.remember.alpha.adapters.ImageAdapter;
import com.remember.alpha.adapters.MemoriesManager;

public class MemoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memories_activity);
        Intent intent = getIntent();
        GridView memoriesGrid = (GridView)findViewById(R.id.memories_grid);
        //Setting  the Memories into a grid look using ImageAdapter
        memoriesGrid.setAdapter(new ImageAdapter(MemoriesManager.getInstance().getMemories(this, new EventManager(this).GetEvents().get(intent.getIntExtra("position", -1)).id),R.layout.memory_item,this));
        memoriesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MemoriesActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
