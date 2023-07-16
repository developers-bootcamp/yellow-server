package com.yellow.ordermanageryellow.dao;

import com.yellow.ordermanageryellow.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
