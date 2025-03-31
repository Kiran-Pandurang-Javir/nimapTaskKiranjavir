package org.techhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.techhub.entity.Category;
import org.techhub.reoisitory.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // This function retrieves all categories with pagination.
    public Page<Category> getAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    // This function retrieves a category based on the given category ID.
    public Category findCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));
    }

    // This function creates a new category.
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // This function updates an existing category based on the given category ID.
    public Category updateCategoryByIdWise(Integer categoryId, Category newCategoryDetails) {
        Category category = findCategoryById(categoryId);
        category.setCategoryName(newCategoryDetails.getCategoryName());
        return categoryRepository.save(category);
    }

    // This function deletes a category based on the given category ID.
    public void deleteCategoryById(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}