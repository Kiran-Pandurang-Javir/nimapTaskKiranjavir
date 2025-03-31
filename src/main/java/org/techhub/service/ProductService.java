package org.techhub.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.techhub.entity.Category;
import org.techhub.entity.Product;
import org.techhub.reoisitory.CategoryRepository;
import org.techhub.reoisitory.ProductRepository;


@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    // This function retrieves all products with pagination.
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    
    // This function retrieves a product based on the given product ID.
    public Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
    }
    
    // This function creates a new product. A valid category must be provided.
    public Product createProduct(Product product) {
        if (product.getCategory() == null || product.getCategory().getCategoryId() == null) {
            throw new RuntimeException("Product must have a valid Category");
        }
        return productRepository.save(product);
    }
    
    // This function updates an existing product based on the given product ID.
    public Product updateProduct(Integer productId, Product newProductDetails) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
        
        product.setProductName(newProductDetails.getProductName());
        product.setProductPrice(newProductDetails.getProductPrice());
        
        if (newProductDetails.getCategory() != null && newProductDetails.getCategory().getCategoryId() != null) {
            Category category = categoryRepository.findById(newProductDetails.getCategory().getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + newProductDetails.getCategory().getCategoryId()));
            product.setCategory(category);
        }

        return productRepository.save(product);
    }
    
    // This function deletes a product based on the given product ID.
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}