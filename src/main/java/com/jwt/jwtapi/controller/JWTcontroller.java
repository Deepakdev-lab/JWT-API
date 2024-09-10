package com.jwt.jwtapi.controller;

import com.jwt.jwtapi.model.Users;
import com.jwt.jwtapi.service.GetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/jwtapi/")
public class JWTcontroller {

    @Autowired
    GetUserService getUserService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        try {
            List<Users> users = getUserService.getUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
