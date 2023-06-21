package com.yellow.ordermanageryellow.Dao;

import com.yellow.ordermanageryellow.model.Orders;
import com.yellow.ordermanageryellow.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepository extends MongoRepository<Orders, String> {
}
