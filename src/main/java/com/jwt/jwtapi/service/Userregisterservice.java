package com.jwt.jwtapi.service;

import com.jwt.jwtapi.model.Users;
import com.jwt.jwtapi.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class Userregisterservice {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();

    public Users register(Users users){
        users.setUserPassword(bCryptPasswordEncoder.encode(users.getUserPassword()));
        return usersRepo.save(users);
    }

}
