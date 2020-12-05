package com.joseludev.locatia.application.newLocation;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.joseludev.locatia.R;
import com.joseludev.locatia.domain.location.LocationManager;


public class NewLocationActivity extends AppCompatActivity implements LocationListener {

    private NewLocationViewModel newLocationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(getString(R.string.new_location));
        setSupportActionBar(myToolbar);

        newLocationViewModel = new NewLocationViewModel(this.getApplication());

        LocationManager.getLocationCurrent(this, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LocationManager.onRequestedLocationPermissionsResult(requestCode, this, this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}