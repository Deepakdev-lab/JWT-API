package com.jwt.jwtapi.controller;

import com.jwt.jwtapi.model.Users;
import com.jwt.jwtapi.service.GetUserService;
import com.jwt.jwtapi.service.JwtBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/jwtapi/")
public class JWTcontroller {

    @Autowired
    GetUserService getUserService;

    @Autowired
    JwtBuilderService jwtBuilderService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers(){
        try {
            List<Users> users = getUserService.getUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getJWTToken")
    public ResponseEntity<?> getJWTToken(){
        try {
//            UserDetails userDetails = userDetailsService.loadUserByUsername("Dev"); // Replace "Dev" with the desired username
//            if (userDetails == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            // Load the user details using the username from Basic Auth
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            String JWTToken = jwtBuilderService.buildToken(userDetails);
            return new ResponseEntity<>(JWTToken, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
