package com.joseludev.locatia.application.newLocation;

import android.app.Application;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


public class NewLocationViewModel extends AndroidViewModel {

    private MutableLiveData<Double> latitude = new MutableLiveData<>(), longitude = new MutableLiveData<>();
    private String name, description;
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
}
