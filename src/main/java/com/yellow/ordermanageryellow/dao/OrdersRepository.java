package com.yellow.ordermanageryellow.dao;

import com.yellow.ordermanageryellow.model.Orders;
import com.yellow.ordermanageryellow.model.Orders.status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrdersRepository extends MongoRepository<Orders, String> {
    public Page<Orders> findByCompanyId_IdAndOrderStatusIdAndEmployee(String token, status status, String userId, Pageable pageNumber);

}
