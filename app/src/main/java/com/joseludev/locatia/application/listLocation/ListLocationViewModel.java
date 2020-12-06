package com.joseludev.locatia.application.listLocation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.joseludev.locatia.domain.database.LocationRoomDatabase;
import com.joseludev.locatia.domain.models.LocationMinimal;

import java.util.List;

public class ListLocationViewModel extends AndroidViewModel {

    private final LiveData<List<LocationMinimal>> locationList;

    public ListLocationViewModel(@NonNull Application application) {
        super(application);
        LocationRoomDatabase db = LocationRoomDatabase.getDatabase(application);
        locationList = db.locationDao().getLocationsMinimal();
    }

    public LiveData<List<LocationMinimal>> getLocationList() {
        return locationList;
    }
}
