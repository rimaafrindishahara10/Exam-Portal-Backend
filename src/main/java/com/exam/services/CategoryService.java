package com.exam.services;

import com.exam.models.exam.Category;

import java.util.Set;

public interface CategoryService {

    public Category addCategory(Category category);
    public Category updateCategory(Category category ,Long categoryId);

    public Set<Category> getCategories ();
    public Category getCategory(Long categoryId);
    public void deleteCategory(Long categoryId);
}
