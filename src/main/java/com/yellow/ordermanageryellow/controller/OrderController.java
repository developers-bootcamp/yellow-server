package com.yellow.ordermanageryellow.controller;
import com.yellow.ordermanageryellow.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yellow.ordermanageryellow.model.Orders;


import java.util.List;
@RestController
@RequestMapping("/Order")
public class OrderController {
    private final OrdersService orderservice;
    @Autowired
    public OrderController(OrdersService orderservice) {this.orderservice=orderservice;}

    @GetMapping("/{userId}/{status}/{pageNumber}")
    public ResponseEntity getOrders(@RequestHeader String token,@PathVariable String userId, @PathVariable String status, @PathVariable int pageNumber){
        try{
            List<Orders> orders= orderservice.getOrders(token,userId,status,pageNumber);
            return ResponseEntity.ok(orders);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }


}
