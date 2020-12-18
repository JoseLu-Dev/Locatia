package com.joseludev.locatia.application.categories;

import com.joseludev.locatia.domain.models.CategoryModel;

public interface CategorySelectionManager {
    void onCategorySelected(CategoryModel categoryModel);
}
