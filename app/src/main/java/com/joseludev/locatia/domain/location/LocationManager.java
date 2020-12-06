package com.joseludev.locatia.domain.location;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;


public class LocationManager {

    public static final int REQUEST_CODE_LOCATION = 0;

    private static FusedLocationProviderClient fusedLocationProviderClient;


    public static void getLocationCurrent(Activity activity, LocationManagerHandler locationManagerHandler) {
        //TODO check if gps is activated
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        if (ContextCompat.checkSelfPermission(
                activity, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                Location location = task.getResult();
                if (location != null) {
                    locationManagerHandler.onLocationChanged(location);
                }
            });

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


    public static void onRequestedLocationPermissionsResult(Activity activity, LocationManagerHandler locationManagerHandler) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                Location location = task.getResult();
                if (location != null) {
                    locationManagerHandler.onLocationChanged(location);
                }
            });
        } else {
            // Explain to the user that the feature is unavailable because
            // the features requires a permission that the user has denied.
            // At the same time, respect the user's decision. Don't link to
            // system settings in an effort to convince the user to change
            // their decision.
            locationManagerHandler.onLocationPermissionDenied();
        }

    }

    public interface LocationManagerHandler {
        void onLocationChanged(Location location);
        void onLocationPermissionDenied();
    }

}
