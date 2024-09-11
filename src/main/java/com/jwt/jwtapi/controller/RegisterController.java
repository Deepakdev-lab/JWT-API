package com.jwt.jwtapi.controller;

import com.jwt.jwtapi.model.Users;
import com.jwt.jwtapi.service.Userregisterservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RegisterController {

    @Autowired
    Userregisterservice userregisterservice;

    @PostMapping("/userRegister")
    public Users userRegister(@RequestBody Users users){
        return userregisterservice.register(users);
    }
}
