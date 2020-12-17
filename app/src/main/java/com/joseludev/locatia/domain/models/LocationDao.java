package com.joseludev.locatia.domain.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LocationModel locationModel);

    @Query("DELETE FROM location_table")
    void deleteAll();

    @Query("SELECT name, photoPath FROM location_table")
    LiveData<List<LocationMinimal>> getLocationsMinimal();

    @Query("SELECT * FROM location_table WHERE name = :name")
    LocationModel getLocationSingle(String name);
}
