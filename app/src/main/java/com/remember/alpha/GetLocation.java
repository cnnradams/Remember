package com.remember.alpha;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class GetLocation {
    private Activity c;
    public GetLocation(Activity c) {
this.c = c;
    }
    public Location getLocation() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(c,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(c,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(c,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        10);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        LocationManager mLocationManager = (LocationManager) c.getSystemService(c.LOCATION_SERVICE);


        return   mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

}
