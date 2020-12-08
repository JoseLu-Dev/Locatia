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
import com.joseludev.locatia.R;
import com.joseludev.locatia.domain.database.LocationRoomDatabase;
import com.joseludev.locatia.domain.models.CategoryModel;
import com.joseludev.locatia.domain.models.LocationDao;
import com.joseludev.locatia.domain.models.LocationModel;
import com.joseludev.locatia.domain.storage.StorageManager;

import java.io.File;
import java.io.IOException;

import static android.provider.Settings.Global.getString;


public class NewLocationViewModel extends AndroidViewModel {

    static String INFORMATION_VALID = "Valid",
            INFORMATION_MISSING_NAME,
            INFORMATION_MISSING_DESCRIPTION,
            INFORMATION_MISSING_PHOTO,
            INFORMATION_MISSING_COORDINATES;

    static final int REQUEST_TAKE_PHOTO = 1;

    private boolean locationSetted = false;
    private MutableLiveData<Double> latitude = new MutableLiveData<>(), longitude = new MutableLiveData<>();
    private String name, description;
    private String photoPath;
    //private Category category; //TODO implement category

    public NewLocationViewModel(@NonNull Application application) {
        super(application);
        INFORMATION_VALID = application.getString(R.string.missing_coordinates);
        INFORMATION_MISSING_DESCRIPTION = application.getString(R.string.missing_coordinates);
        INFORMATION_MISSING_PHOTO = application.getString(R.string.missing_coordinates);
        INFORMATION_MISSING_COORDINATES = application.getString(R.string.missing_coordinates);
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

    public String informationValid() {
        if (name == null || name.length() == 0) {
            return NewLocationViewModel.INFORMATION_MISSING_NAME;
        } else if (description == null || description.length() == 0) {
            return NewLocationViewModel.INFORMATION_MISSING_DESCRIPTION;
        } else if (photoPath == null || photoPath.length() == 0) {
            return NewLocationViewModel.INFORMATION_MISSING_PHOTO;
        } else if (!locationSetted) {
            return NewLocationViewModel.INFORMATION_MISSING_COORDINATES;
        }
        return NewLocationViewModel.INFORMATION_VALID;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        if (photoPath == null) {
            File photo = new File(this.photoPath);
            photo.delete();
        }
        this.photoPath = photoPath;
    }

    public void setLocation(Location location) {
        latitude.setValue(location.getLatitude());
        longitude.setValue(location.getLongitude());
        locationSetted = true;
    }

    public LocationModel getLocationItem() {
        return new LocationModel(latitude.getValue(), longitude.getValue(), name, description, photoPath, null);
    }

    public void saveLocationOnDatabase(Application application) {
        LocationModel locationModel = getLocationItem();
        LocationRoomDatabase db = LocationRoomDatabase.getDatabase(application);
        LocationDao locationDao = db.locationDao();
        LocationRoomDatabase.getDatabaseWriteExecutor().execute(() -> {
            locationDao.insert(locationModel);
        });
    }
}
