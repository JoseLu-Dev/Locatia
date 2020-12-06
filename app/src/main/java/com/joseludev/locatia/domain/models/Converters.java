package com.joseludev.locatia.domain.models;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static CategoryModel fromCategoryString(String category) {
        return  category == null ? null : new CategoryModel(category);
    }

    @TypeConverter
    public static String categoryToString(CategoryModel categoryModel) {
        return categoryModel == null ? null : categoryModel.getCategory();
    }
}
