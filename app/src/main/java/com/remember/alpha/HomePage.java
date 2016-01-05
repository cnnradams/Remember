package com.remember.alpha;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.getbase.floatingactionbutton.FloatingActionsMenu;

public class HomePage extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CardViewAdapter mAdapter;
    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //You can only set status bar color with lollipop and above :(
        if (Build.VERSION.SDK_INT >= 21) {

            getWindow().setStatusBarColor(Color.BLUE);

        }
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView)findViewById(R.id.list);
       // if(isTablet(this)) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
       // } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
       // }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new CardViewAdapter(TimeLineManager.getInstance().gettimeLines(), R.layout.row_timeline, this);
        mRecyclerView.setAdapter(mAdapter);


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
        TakePicture takePic = new TakePicture("Remember Picture " +
                "",HomePage.this);
       takePic.capturePicture();
    }
}
