package com.joseludev.locatia.application.newLocation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.joseludev.locatia.R;
import com.joseludev.locatia.databinding.ActivityLocationBinding;
import com.joseludev.locatia.databinding.ActivityNewLocationActivityBinding;
import com.joseludev.locatia.domain.location.LocationManager;
import com.joseludev.locatia.domain.models.CategoryModel;
import com.joseludev.locatia.domain.models.LocationModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.joseludev.locatia.application.newLocation.NewLocationViewModel.REQUEST_TAKE_PHOTO;


public class NewLocationActivity extends AppCompatActivity implements LocationManager.LocationManagerHandler {

    private NewLocationViewModel newLocationViewModel;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNewLocationActivityBinding activityNewLocationActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_new_location_activity);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitle(getString(R.string.new_location));
        setSupportActionBar(myToolbar);

        newLocationViewModel = new NewLocationViewModel(this.getApplication());
        activityNewLocationActivityBinding.setViewModel(newLocationViewModel);

        LocationManager.getLocationCurrent(this, this);

        imageView = findViewById(R.id.imageView);

        setObservers();
        setOnTextChangedListeners();

        setSpinner();
    }

    private void setSpinner(){
        Spinner spinner = findViewById(R.id.spinner);

        newLocationViewModel.getFirstsCategories(this.getApplication()).observe(this, categoryModels -> {
            String[] array = new String[categoryModels.size()];

            for(int i = 0; i < array.length; i++){
                array[i] = categoryModels.get(i).getCategory();
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter<>(NewLocationActivity.this, android.R.layout.simple_list_item_1, array);
            spinner.setAdapter(arrayAdapter);
        });
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
        //TODO change for data data binding
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