package com.exam.services.impl;

import com.exam.models.exam.Category;
import com.exam.repositories.CategoryRepo;
import com.exam.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category addCategory(Category category) {
        return this.categoryRepo.save (category);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category category1 = this.categoryRepo.findById ( categoryId ).get ();
        category1.setCId ( category.getCId () );
        category1.setTitle ( category.getTitle ());
        category1.setDescription ( category.getDescription () );
        return this.categoryRepo.save ( category1 );
    }


    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<> (this.categoryRepo.findAll ());
    }

    @Override
    public Category getCategory(Long categoryId) {
        return this.categoryRepo.findById ( categoryId ).get ();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        this.categoryRepo.deleteById ( categoryId );
    }
}
