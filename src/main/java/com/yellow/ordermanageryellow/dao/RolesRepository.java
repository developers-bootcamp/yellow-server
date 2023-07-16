package com.yellow.ordermanageryellow.dao;

import com.yellow.ordermanageryellow.model.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RolesRepository extends MongoRepository<Roles, String> {
}
