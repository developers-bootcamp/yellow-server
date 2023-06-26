package com.yellow.ordermanageryellow.Dao;

import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String> {
    List<ProductCategory> findAll();
    ProductCategory save(ProductCategory productCategory);
    // Delete
    void deleteById(String categoryId);

    // Edit
    ProductCategory update(ProductCategory productCategory);

    // Retrieve all

}
