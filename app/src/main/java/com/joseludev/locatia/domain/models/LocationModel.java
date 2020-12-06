package com.joseludev.locatia.domain.models;

public class LocationModel {

    private double latitude, longitude;
    private String name, description, photoPath;
    private CategoryModel categoryModel;

    public LocationModel(double latitude, double longitude, String name, String description, String photoPath, CategoryModel categoryModel) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.description = description;
        this.photoPath = photoPath;
        this.categoryModel = categoryModel;
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", categoryModel=" + categoryModel +
                '}';
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
