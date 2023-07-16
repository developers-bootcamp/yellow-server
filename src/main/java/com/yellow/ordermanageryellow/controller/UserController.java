package com.yellow.ordermanageryellow.controller;

import com.yellow.ordermanageryellow.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")

@RestController
@RequestMapping("/User")
public class UserController {

    private final UsersService usersService;
    @Autowired
    public UserController(UsersService usersService){
        this.usersService=usersService;
    }
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam  String password, @RequestParam String email){
        return usersService.login(email,password);
    }



}
