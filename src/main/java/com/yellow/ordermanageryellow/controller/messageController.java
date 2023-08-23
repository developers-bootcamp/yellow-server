package com.yellow.ordermanageryellow.controller;

import com.yellow.ordermanageryellow.Dto.OrderDTO;
import com.yellow.ordermanageryellow.model.Orders;
import com.yellow.ordermanageryellow.model.Try;
import com.yellow.ordermanageryellow.service.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class messageController {
    private RabbitMQProducer p;

    public messageController(RabbitMQProducer p) {
        this.p = p;
    }
    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody OrderDTO message){
        p.sendMessage(message);
        return ResponseEntity.ok("!!!!");
    }
}

