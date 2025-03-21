package com.cs308.backend.services;

import com.cs308.backend.models.Category;
import com.cs308.backend.repositories.CategoryRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<String> addCategory(Category category, String categoryName) {
        if (categoryRepository.existsByCategoryName(categoryName))
            return ResponseEntity.badRequest().body("Category with this name already exists!");
        if (categoryName == null || categoryName == "")
            return ResponseEntity.badRequest().body("Category name cannot be null or empty!");
        category.setCategoryName(categoryName);
        category.setProductIds(new ArrayList<>());
        categoryRepository.save(category);
        return ResponseEntity.ok("Category added successfully!");
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


}