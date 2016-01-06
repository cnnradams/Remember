package com.remember.alpha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.remember.alpha.adapters.ImageAdapter;

public class MemoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memories_activity);

        GridView memoriesGrid = (GridView)findViewById(R.id.memories_grid);
        memoriesGrid.setAdapter(new ImageAdapter(this));
        memoriesGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MemoriesActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
