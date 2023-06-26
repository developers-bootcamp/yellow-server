package com.yellow.ordermanageryellow.controller;

import com.yellow.ordermanageryellow.DTO.LoginRequest;
import com.yellow.ordermanageryellow.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Uesr")
public class UserController {

    private final UsersService usersService;
    @Autowired
    public UserController(UsersService usersService){
        this.usersService=usersService;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        String password=loginRequest.getPassword();
        String email=loginRequest.getEmail();

        return usersService.login(email,password);
    }



}
