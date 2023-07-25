package com.yellow.ordermanageryellow.dao;

import com.yellow.ordermanageryellow.model.Product;
import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String> {
    boolean existsByname(String name);
}
