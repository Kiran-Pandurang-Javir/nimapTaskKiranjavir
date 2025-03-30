package org.techhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.techhub.entity.Category;
import org.techhub.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping
    public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable) {
        Page<Category> categories = categoryService.getAllCategories(pageable);
        return ResponseEntity.ok(categories);
    }
    
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId) {
        Category category = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }
    
    @PostMapping
    public ResponseEntity<Category> createNewCategory(@RequestBody Category category) {
        Category addCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(addCategory);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, 
                                                 @RequestBody Category newCategoryDetails) {
        Category updatedCategory = categoryService.updateCategoryByIdWise(id, newCategoryDetails);
        return ResponseEntity.ok(updatedCategory);    
    }
    
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Integer categoryId) {
        categoryService.deleteCategoryById(categoryId);    
        return ResponseEntity.noContent().build();
    }
}