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

	// This function retrieves for category with pagination
	@GetMapping
	public ResponseEntity<Page<Category>> getAllCategories(Pageable pageable) {
		Page<Category> categories = categoryService.getAllCategories(pageable);
		return ResponseEntity.ok(categories);
	}

	// This function retrieves a category based on the given category ID.
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId) {
		Category category = categoryService.findCategoryById(categoryId);
		return ResponseEntity.ok(category);
	}

	// This function creates a new category.
	@PostMapping
	public ResponseEntity<Category> createNewCategory(@RequestBody Category category) {
		Category addCategory = categoryService.createCategory(category);
		return ResponseEntity.ok(addCategory);
	}

	// This function updates an existing category based on the given category ID.
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category newCategoryDetails) {
		Category updatedCategory = categoryService.updateCategoryByIdWise(id, newCategoryDetails);
		return ResponseEntity.ok(updatedCategory);
	}

	// This function deletes a category based on the given category ID.
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Void> deleteCategoryById(@PathVariable Integer categoryId) {
		categoryService.deleteCategoryById(categoryId);
		return ResponseEntity.noContent().build();
	}
}