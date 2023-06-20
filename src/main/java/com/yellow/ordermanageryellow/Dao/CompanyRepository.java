package com.yellow.ordermanageryellow.Dao;

import com.yellow.ordermanageryellow.model.Address;
import com.yellow.ordermanageryellow.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String> {
}
