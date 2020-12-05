package com.joseludev.locatia.domain.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;

import androidx.core.content.ContextCompat;


public class LocationManager {

    public static final int REQUEST_CODE_LOCATION = 0;

    private static android.location.LocationManager locationManager;

    public static void getLocationCurrent(Activity activity, LocationListener locationListener) {
        if (ContextCompat.checkSelfPermission(
                activity, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            locationManager = (android.location.LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestSingleUpdate(android.location.LocationManager.GPS_PROVIDER, locationListener, null);

        } else {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            activity.requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION);
        }
    }

    public static void onRequestedLocationPermissionsResult(int requestCode, Activity activity, LocationListener locationListener) {


        if (ContextCompat.checkSelfPermission(
                activity, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            locationManager = (android.location.LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestSingleUpdate(android.location.LocationManager.GPS_PROVIDER, locationListener, null);

        } else {
            // Explain to the user that the feature is unavailable because
            // the features requires a permission that the user has denied.
            // At the same time, respect the user's decision. Don't link to
            // system settings in an effort to convince the user to change
            // their decision.
            locationListener.onProviderDisabled("");
        }

    }

}
