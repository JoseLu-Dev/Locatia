package com.joseludev.locatia.application.newLocation;

import androidx.appcompat.widget.Toolbar;

import android.location.Location;
import android.os.Bundle;

import com.joseludev.locatia.R;
import com.yayandroid.locationmanager.base.LocationBaseActivity;
import com.yayandroid.locationmanager.configuration.Configurations;
import com.yayandroid.locationmanager.configuration.LocationConfiguration;

public class newLocationActivity extends LocationBaseActivity {

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onLocationFailed(int type) {
        //TODO let to know the user the location failed
    }

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return Configurations.defaultConfiguration(
                "The app needs the location permission" +
                        " in order to work correctly",
                "Would you mind to turn GPS on?");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(getString(R.string.new_location));
        setSupportActionBar(myToolbar);
    }
}