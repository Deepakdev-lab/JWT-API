package com.jwt.jwtapi.service;

import com.jwt.jwtapi.model.MyUserPrincipal;
import com.jwt.jwtapi.model.Users;
import com.jwt.jwtapi.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = usersRepo.findByUserName(userName);
        return new MyUserPrincipal(users);
    }
}