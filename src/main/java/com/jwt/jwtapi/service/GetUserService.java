package com.jwt.jwtapi.service;

import com.jwt.jwtapi.model.Users;
import com.jwt.jwtapi.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserService {

    @Autowired
    UsersRepo usersRepo;

    public List<Users> getUsers(){
        List<Users> usersList = usersRepo.findAll();
        if(usersList.isEmpty())
            throw new RuntimeException ("Users does not exist");
        return usersList;
    }
}
