package com.yellow.ordermanageryellow.service;

import com.yellow.ordermanageryellow.dao.OrdersRepository;
import com.yellow.ordermanageryellow.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


@Service
public class OrdersService implements CommandLineRunner {
    private final OrdersRepository OrdersRepository;
    @Autowired
    public OrdersService(OrdersRepository OrdersRepository) {
        this.OrdersRepository = OrdersRepository;
    }
    @Override
    public void run(String... args) {
        Orders Orders = new Orders("12");
        OrdersRepository.save(Orders);
    }
}