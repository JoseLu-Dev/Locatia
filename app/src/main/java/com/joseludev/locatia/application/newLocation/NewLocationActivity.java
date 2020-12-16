package com.joseludev.locatia.application.newLocation;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.joseludev.locatia.R;
import com.joseludev.locatia.databinding.ActivityNewLocationActivityBinding;
import com.joseludev.locatia.domain.location.LocationManager;

import java.io.File;

import static com.joseludev.locatia.application.newLocation.NewLocationViewModel.REQUEST_TAKE_PHOTO;


public class NewLocationActivity extends AppCompatActivity implements LocationManager.LocationManagerHandler {

    private ActivityNewLocationActivityBinding activityNewLocationActivityBinding;
    private NewLocationViewModel newLocationViewModel;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityNewLocationActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_new_location_activity);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle(getString(R.string.new_location));
        setSupportActionBar(myToolbar);

        newLocationViewModel = new NewLocationViewModel(this.getApplication());
        activityNewLocationActivityBinding.setViewModel(newLocationViewModel);

        imageView = findViewById(R.id.imageView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocationManager.getLocationCurrent(this);
    }

    private void setSpinner() {
        Spinner spinner = findViewById(R.id.spinner);

        newLocationViewModel.getFirstsCategories().observe(this, categoryModels -> {
            String[] array = new String[categoryModels.size()];

            for (int i = 0; i < array.length; i++) {
                array[i] = categoryModels.get(i).getCategory();
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter<>(NewLocationActivity.this, android.R.layout.simple_list_item_1, array);
            spinner.setAdapter(arrayAdapter);
        });
    }

    public void onCategoryAddButtonClicked(View view) {
        //TODO implement
    }

    public void onTakePictureButtonClicked(View view) {
        newLocationViewModel.takePicture(this);
    }

    public void onCheckButtonClicked(View view) {
        String information = newLocationViewModel.informationValid();
        if (information.equals(NewLocationViewModel.INFORMATION_VALID)) {
            newLocationViewModel.saveLocationOnDatabase(this.getApplication());
            finish();
        } else {
            Toast.makeText(this, information, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                File f = new File(newLocationViewModel.getPhotoPath());
                Uri contentUri = Uri.fromFile(f);
                imageView.setImageURI(contentUri);
            } else {
                newLocationViewModel.setPhotoPath(null);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LocationManager.REQUEST_CODE_LOCATION:
                LocationManager.onRequestedLocationPermissionsResult(this);
                break;
            case 1:
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        newLocationViewModel.setLocation(location);
        activityNewLocationActivityBinding.invalidateAll();
    }

    @Override
    public void onLocationPermissionDenied() {

    }

}