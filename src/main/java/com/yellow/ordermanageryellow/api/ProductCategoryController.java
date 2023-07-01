package com.yellow.ordermanageryellow.api;
import com.yellow.ordermanageryellow.Service.ProductCategoryService;
import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        return productCategoryService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody ProductCategory newCategory) {
        return productCategoryService.insert(newCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable String categoryId) {
        return productCategoryService.delete(categoryId);
    }

    @PutMapping
    public ResponseEntity<ProductCategory> updateCategory(@RequestBody ProductCategory updateCategory) {
        return productCategoryService.update(updateCategory);
    }
}
