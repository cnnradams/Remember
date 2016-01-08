package com.remember.alpha;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.remember.alpha.adapters.CardViewAdapter;
import com.remember.alpha.adapters.ItemClickSupport;

public class HomePage extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CardViewAdapter mAdapter;
    @Override
@SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Sets Toolbars Colors
        toolbar.setBackgroundColor(getResources().getColor(R.color.actionBarBackground));
        toolbar.setTitleTextColor(getResources().getColor(R.color.actionBarText));
        //You can only set status bar color with lollipop and above :(
        if (Build.VERSION.SDK_INT >= 21) {

            getWindow().setStatusBarColor(getResources().getColor(R.color.statusBarBackground));

        }
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView)findViewById(R.id.list);
        if(isTablet(this)) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //Sets the Event Adapter to a CardView
        mAdapter = new CardViewAdapter( new EventManager(this).GetEvents() , R.layout.row_timeline, this);
        mRecyclerView.setAdapter(mAdapter);
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                //Goes to Memories Page with knowledge of which card was clicked

                Intent intent = new Intent(HomePage.this,MemoriesActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void takePicture(View view) {
    /*    TakePicture takePic = new TakePicture("Remember Picture " +
                "",HomePage.this);
       takePic.capturePicture();*/
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(isTablet(this)) {
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                mRecyclerView.setAdapter(mAdapter);
            }
            else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                mRecyclerView.setAdapter(mAdapter);
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            if(isTablet(this)) {
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                mRecyclerView.setAdapter(mAdapter);
            } else{
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }
    public void createEvent(View view) {
Intent intent = new Intent(this,CreateEventActivity.class);
        startActivity(intent);
    }
    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }
}
