package com.joseludev.locatia.domain.models;

public class LocationMinimal {
    private String name;
    private String photoPath;

    public LocationMinimal(String name, String photoPath) {
        this.name = name;
        this.photoPath = photoPath;
    }

    public String getName() {
        return name;
    }

    public String getPhotoPath() {
        return photoPath;
    }
}
