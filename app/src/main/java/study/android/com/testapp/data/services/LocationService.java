package study.android.com.testapp.data.services;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.util.Log;


import study.android.com.testapp.MainActivity;
import study.android.com.testapp.data.model.LocationModel;

/**
 * Created by tony on 30.03.16.
 */
public class LocationService {

    //The minimum distance to change updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    //The minimum time beetwen updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    private final static boolean forceNetwork = false;
    private static final int MY_PERMISSION_REQUEST_LOCATION_GET = 1;
    public static int MY_PERMISSION_LOCATION = 1;

    public String TAG = "LocationService";
    private LocationManager locationManager;
    public Location location;
    ;
    public double longitude;
    public double latitude;
    private boolean isGPSEnabled;
    private boolean isNetworkEnabled;
    private boolean locationServiceAvailable;
    private Context context;
    public boolean statusLocation;
    public double[] coordBox = new double[4];
    public LocationModel locationObject;
    private Handler handler;


    public LocationService(Context context, LocationManager locationManager) {
        this.context = context;
        this.locationManager = locationManager;

    }


    /**
     * Singleton implementation
     *
     * @return
     */


    private void getGeoPossition(double latitude, double longitude) {

        if (latitude != 0 && longitude != 0) {
            double east;
            double west;
            double north;
            double south;
            double degreeLat = 12.5 / 111.12;
            double degreeLong = (12.5 / 111.12) / Math.cos(Math.toRadians(latitude));
            east = longitude + degreeLong;
            west = longitude - degreeLong;
            north = latitude + degreeLat;
            south = latitude - degreeLat;
            coordBox = new double[]{east, west, north, south};
            locationObject = new LocationModel(longitude, latitude, coordBox);
        }

    }


    @TargetApi(Build.VERSION_CODES.M)
    public String marshmallowGPSPremissionCheck() {
        String provider = null;
        Log.i(TAG, "----------------Method:marshmallowGPSPremissionCheck()-------------------------" + "\n " + "Check all permission ");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Do Request Permissions ");
            try {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_LOCATION);
                Log.i(TAG, "\n" + " -----------------------------------------");
            } catch (Exception e) {
                Log.e(TAG, "Error while do Request Permission: " + e.getMessage() + "\n" + " -----------------------------------------");
                return null;
            }

        }
//        else {


        try {

            // getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
//                Log.i(TAG, "Getting GPS status: " + isGPSEnabled);
            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//                Log.i(TAG, " Getting Network status: " + isNetworkEnabled);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
                return null;
            } else {

                if (isNetworkEnabled) {
//                        Log.i(TAG, "Network Enabled");
                    provider = LocationManager.NETWORK_PROVIDER;


                }
                // if GPS =Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
//                        Log.i(TAG, "GPS Enabled");

                    provider = LocationManager.GPS_PROVIDER;

                }
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
//        }
        return provider;

    }

    public LocationModel callUIThreadForLocation(final String provider) {
        Log.i(TAG, "------------------Method: callUIThreadForLocation-----------------------" + "\n" + "Provider: " + provider);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        if (provider != null) {
            locationManager.requestLocationUpdates(provider, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (locationManager != null) {
                location = locationManager.getLastKnownLocation(provider);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    Log.i(TAG, "latitude: " + latitude + "longitude: " + longitude);
                    getGeoPossition(latitude, longitude);
                    final LocationModel locationModel = new LocationModel(longitude, latitude, coordBox);
                    return locationModel;
                } else {
                    Log.i(TAG, "location: Null " + "\n" + " -----------------------------------------");

                    return null;
                }
            } else {
                Log.i(TAG, "locationManager: Null " + "\n" + " -----------------------------------------");

                return null;
            }
        }
        Log.i(TAG, "Provider: Null " + "\n" + " -----------------------------------------");
        return null;
    }

    LocationListener locationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            Log.i(TAG, "onLocationChanged " + "\n" + " -----------------------------------------" + "\n" + "latitude: " + latitude + "longitude: " + longitude);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}