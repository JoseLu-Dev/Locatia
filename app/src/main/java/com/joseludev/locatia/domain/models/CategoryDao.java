package com.joseludev.locatia.domain.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CategoryModel categoryModel);

    @Query("DELETE FROM category_table")
    void deleteAll();

    @Query("SELECT * FROM category_table")
    LiveData<List<CategoryModel>> getCategories();

    @Query("SELECT * FROM category_table LIMIT 0, 4")
    LiveData<List<CategoryModel>> getFirstsCategories();
}
