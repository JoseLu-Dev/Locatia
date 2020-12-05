package com.joseludev.locatia.application.newLocation;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.Observer;

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

        setObservers();
        setOnTextChangedListeners();
    }

    private void setObservers() {
        TextView editTextLatitude = findViewById(R.id.editTextLatitude);
        TextView editTextLongitude = findViewById(R.id.editTextLongitude);

        final Observer<Double> latitudeObserver = aDouble -> {
            editTextLatitude.setText(String.valueOf(aDouble));
        };
        newLocationViewModel.getLatitude().observe(this, latitudeObserver);

        final Observer<Double> longitudeObserver = aDouble -> {
            editTextLongitude.setText(String.valueOf(aDouble));
        };
        newLocationViewModel.getLongitude().observe(this, longitudeObserver);
    }

    private void setOnTextChangedListeners() {
        EditText editTextName, editTextDescription;

        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextTextMultiLineDescription);

        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newLocationViewModel.setName(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newLocationViewModel.setDescription(String.valueOf(s));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LocationManager.onRequestedLocationPermissionsResult(requestCode, this, this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        newLocationViewModel.setLocation(location);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}