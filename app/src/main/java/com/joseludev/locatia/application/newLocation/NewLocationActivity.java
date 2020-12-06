package com.joseludev.locatia.application.newLocation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import java.io.File;

import static com.joseludev.locatia.application.newLocation.NewLocationViewModel.REQUEST_TAKE_PHOTO;


public class NewLocationActivity extends AppCompatActivity implements LocationManager.LocationManagerHandler {

    private NewLocationViewModel newLocationViewModel;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(getString(R.string.new_location));
        setSupportActionBar(myToolbar);

        newLocationViewModel = new NewLocationViewModel(this.getApplication());

        LocationManager.getLocationCurrent(this, this);

        imageView = findViewById(R.id.imageView);

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
        newLocationViewModel.takePicture(this);
    }

    public void onCheckButtonClicked(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File(newLocationViewModel.photoPath);
            Uri contentUri = Uri.fromFile(f);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);
            imageView.setImageURI(contentUri);
            //TODO move to viewmodel
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
    public void onLocationChanged(Location location) {
        newLocationViewModel.setLocation(location);
    }

    @Override
    public void onLocationPermissionDenied() {

    }
}