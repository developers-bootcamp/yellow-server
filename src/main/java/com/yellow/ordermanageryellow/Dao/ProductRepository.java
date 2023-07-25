package com.yellow.ordermanageryellow.dao;

import com.yellow.ordermanageryellow.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    boolean existsByName(String name);

    @Query("{'name': { $regex: '^?1' }}")
    List<Product> findByCompanyIdAndNameAndPrefix(String companyId, String prefix);
    Product findByName(String name);
}
