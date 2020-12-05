package com.joseludev.locatia.application.newLocation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import com.joseludev.locatia.R;
import com.joseludev.locatia.domain.location.LocationManager;


public class NewLocationActivity extends AppCompatActivity implements LocationListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;

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

    public void onCategoryAddButtonClicked(View view) {

    }

    public void onTakePictureButtonClicked(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }

    public void onCheckButtonClicked(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LocationManager.REQUEST_CODE_LOCATION:
                LocationManager.onRequestedLocationPermissionsResult(this, this);
                break;
            case 1:
                break;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        newLocationViewModel.setLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

}