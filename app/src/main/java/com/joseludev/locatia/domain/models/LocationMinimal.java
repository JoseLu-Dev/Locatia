package com.joseludev.locatia.domain.models;

public class LocationMinimal {
    private String name;
    private String photoPath;
    private double latitude, longitude;

    public LocationMinimal(String name, String photoPath, double latitude, double longitude) {
        this.name = name;
        this.photoPath = photoPath;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
