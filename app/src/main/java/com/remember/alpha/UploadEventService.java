package com.remember.alpha;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class UploadEventService extends BroadcastReceiver{
private String name;
    private double latitude;
    private double longitude;
    private boolean privacy;
    private String locationName;
    public Context thisContext;
    @Override
    public void onReceive(Context context, Intent intent) {
this.thisContext = context;
        Bundle extras = intent.getExtras();
        name = extras.getString("name");
       latitude =  extras.getDouble("latitude", -1);
        longitude = extras.getDouble("longitude", -1);
        privacy =  extras.getBoolean("privacy", true);//TODO
         locationName = extras.getString("locationName");
        Log.e("here2","here2");

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // not connected to the internet

            uploadEvent(name,latitude,longitude,privacy,locationName,context);
        } else {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    Intent repeatIntent = new Intent(thisContext,UploadEventService.class);
                    try {
                        Thread.sleep(1000);
                    } catch(java.lang.InterruptedException e) {
                        e.printStackTrace();
                    }
                    repeatIntent.putExtra("name",name);
                    repeatIntent.putExtra("latitude",latitude);
                    repeatIntent.putExtra("longitude",longitude);
                    repeatIntent.putExtra("privacy",privacy);//TODO
                    repeatIntent.putExtra("locationName",locationName);
                    thisContext.sendBroadcast(repeatIntent);
                }
                });
thread.start();
        }
    }




    @SuppressWarnings("deprecation")
        public void uploadEvent(String name,double latitude,double longitude, boolean privacy, String locationName, Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork == null) { // not connected to the internet

        }
            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost("http://corriges.info/remember/uploadEvent.php?event_name=&owner_user=&loc_lat=&loc_lng=&private_event=&loc_name=");


            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<>(6);
            nameValuePair.add(new BasicNameValuePair("event_name", name));
            nameValuePair.add(new BasicNameValuePair("owner_user", "Nothing here yet..."));//TODO USERS
            nameValuePair.add(new BasicNameValuePair("loc_lat", "" + latitude));
            nameValuePair.add(new BasicNameValuePair("loc_lng", "" + longitude));
            nameValuePair.add(new BasicNameValuePair("private_event", "" + privacy));
        nameValuePair.add(new BasicNameValuePair("loc_name", locationName));
            //Encoding POST data
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            } catch (UnsupportedEncodingException e) {
                // log exception
                e.printStackTrace();
            }

            //making POST request.
            try {
                HttpResponse response = httpClient.execute(httpPost);
                // write response to log
                Log.d("Http Post Response:", response.toString());
            } catch (ClientProtocolException e) {
                // Log exception
                e.printStackTrace();
            } catch (IOException e) {
                // Log exception
                e.printStackTrace();
            }
        }
        }
