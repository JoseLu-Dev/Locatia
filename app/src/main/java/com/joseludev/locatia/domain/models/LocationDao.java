package com.joseludev.locatia.domain.models;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LocationModel locationModel);

    @Query("DELETE FROM location_table")
    void deleteAll();

    @Query("SELECT * FROM location_table")
    LiveData<List<LocationModel>> getLocations();
}
