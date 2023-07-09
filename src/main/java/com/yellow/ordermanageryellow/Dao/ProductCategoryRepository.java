package com.yellow.ordermanageryellow.Dao;

import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String> {
    boolean existsByname(String name);
}
