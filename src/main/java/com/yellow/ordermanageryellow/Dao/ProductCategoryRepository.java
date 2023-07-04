package com.yellow.ordermanageryellow.Dao;

import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String> {
}
