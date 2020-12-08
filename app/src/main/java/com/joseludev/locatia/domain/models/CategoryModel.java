package com.joseludev.locatia.domain.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class CategoryModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "category")
    private final String category;

    public CategoryModel(@NonNull String category) {
        this.category = category;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category;
    }
}
