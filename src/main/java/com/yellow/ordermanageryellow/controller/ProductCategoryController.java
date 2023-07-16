package com.yellow.ordermanageryellow.controller;

import com.yellow.ordermanageryellow.exceptions.ObjectAlreadyExistException;
import com.yellow.ordermanageryellow.service.ProductCategoryService;
import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity getAllCategories() {
        List<ProductCategory> categories;
        try {
            categories = productCategoryService.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody ProductCategory newCategory) {
        ProductCategory createdCategory;
        try {
            createdCategory = productCategoryService.insert(newCategory);
            return new ResponseEntity<>(createdCategory, HttpStatus.OK);
        } catch (ObjectAlreadyExistException ex) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable String categoryId) {
        try {
            productCategoryService.delete(categoryId);
            return new ResponseEntity(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody ProductCategory updatedCategory) {
        try {
            ProductCategory updateItem = productCategoryService.update(updatedCategory);
            return ResponseEntity.ok(updateItem);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
