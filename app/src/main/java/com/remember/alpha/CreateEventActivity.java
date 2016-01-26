package com.remember.alpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.Toast;

import com.remember.alpha.R;

import java.util.ArrayList;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create_event);
    }
    //When they hit create
public void finishEventCreation(View view) {
    EditText name = (EditText)findViewById(R.id.editTextName);
    String nameString = name.getText().toString();
    EditText description = (EditText)findViewById(R.id.editTextDescription);
    String descriptionString = description.getText().toString();
    Switch privacy = (Switch)findViewById(R.id.privacySwitch);
    ArrayList<String> members = new ArrayList<String>();
    members.add("Set this up with Members");
    boolean isPublic = privacy.isChecked();
    Log.e("public","" + isPublic);
    EventManager eventManager = new EventManager(this);
    eventManager.NewEvent(nameString, members,isPublic);

    Toast.makeText(this,"Event Made!",Toast.LENGTH_LONG ).show();
    Intent intent = new Intent(this,HomePage.class) ;
    startActivity(intent);
}
}

