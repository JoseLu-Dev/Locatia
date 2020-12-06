package com.joseludev.locatia.domain.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "location_table")
public class LocationModel {

    public static final String LOCATION_NAME = "locationName";

    @NonNull
    @ColumnInfo(name = "latitude")
    private double latitude;

    @NonNull
    @ColumnInfo(name = "longitude")
    private double longitude;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "photoPath")
    private String photoPath;


    @ColumnInfo(name = "category")
    private CategoryModel categoryModel;

    public LocationModel(double latitude, double longitude, String name, String description, String photoPath, CategoryModel categoryModel) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.description = description;
        this.photoPath = photoPath;
        this.categoryModel = categoryModel;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }
}
