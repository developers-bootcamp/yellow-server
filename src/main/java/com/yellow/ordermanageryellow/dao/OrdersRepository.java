package com.yellow.ordermanageryellow.dao;

import com.yellow.ordermanageryellow.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepository extends MongoRepository<Orders, String> {
}
