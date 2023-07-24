package com.yellow.ordermanageryellow.dao;

import com.yellow.ordermanageryellow.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
    boolean existsByName(String name);
}
