package com.joseludev.locatia.application.newLocation;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.joseludev.locatia.BuildConfig;
import com.joseludev.locatia.domain.storage.StorageManager;

import java.io.File;
import java.io.IOException;


public class NewLocationViewModel extends AndroidViewModel {

    static final int REQUEST_TAKE_PHOTO = 1;

    private MutableLiveData<Double> latitude = new MutableLiveData<>(), longitude = new MutableLiveData<>();
    private String name, description;
    String photoPath;
    //private Category category; //TODO implement category

    public NewLocationViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Double> getLatitude() {
        return latitude;
    }

    public void setLatitude(MutableLiveData<Double> latitude) {
        this.latitude = latitude;
    }

    public MutableLiveData<Double> getLongitude() {
        return longitude;
    }

    public void setLongitude(MutableLiveData<Double> longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(Location location) {
        latitude.setValue(location.getLatitude());
        longitude.setValue(location.getLongitude());
    }

    public void takePicture(Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent

        // Create the File where the photo should go
        File photoFile = null;
        try {
            photoFile = StorageManager.createImageFile(activity);
            photoPath = StorageManager.getCurrentPhotoPath();
        } catch (IOException ex) {
            // Error occurred while creating the File

        }
        // Continue only if the File was successfully created
        if (photoFile != null) {
            Uri photoURI = FileProvider.getUriForFile(activity,
                    BuildConfig.APPLICATION_ID + ".provider",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

        }
    }
}
