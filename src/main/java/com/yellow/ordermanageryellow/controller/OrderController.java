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

    @PostMapping
    public ResponseEntity insert(@RequestBody Orders newOrder){
        try{
       String orderId= orderservice.insert(newOrder);
            return ResponseEntity.ok((orderId));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }
    }

    @PutMapping
    public ResponseEntity edit(@PathVariable String id,@RequestBody Orders currencyOrder){
        try{
        boolean isEdit=orderservice.edit(id,currencyOrder);
        if(isEdit){
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found order");
        }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }

    }
}
