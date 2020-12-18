package com.joseludev.locatia.application.listLocation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.joseludev.locatia.domain.database.LocationRoomDatabase;
import com.joseludev.locatia.domain.models.LocationMinimal;

import java.util.List;

public class ListLocationViewModel extends AndroidViewModel {

    private LocationRoomDatabase db;

    public ListLocationViewModel(@NonNull Application application) {
        super(application);
        db = LocationRoomDatabase.getDatabase(application);
    }

    public LiveData<List<LocationMinimal>> getLocationList() {
        return db.locationDao().getLocationsMinimal();
    }

    public LiveData<List<LocationMinimal>> getLocationListByName(String name) {
        return db.locationDao().getLocationsMinimalByName(name);
    }
}
