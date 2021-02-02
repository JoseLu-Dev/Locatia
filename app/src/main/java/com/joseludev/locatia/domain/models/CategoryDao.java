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
    List<CategoryModel> getCategories();

    @Query("SELECT * FROM category_table ORDER BY rowid DESC LIMIT 4")
    LiveData<List<CategoryModel>> getLastCategories();

    @Query("SELECT l.category, count(*) FROM location_table as l group by l.category")
    List<CategoryAndCountModel> getCategoriesAndCount();
}
