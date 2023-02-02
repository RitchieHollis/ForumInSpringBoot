package com.projet.forum.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Repositories.UserRepository;
import com.projet.forum.Services.UserServices.UserServiceImpl;

@RestController("/users")
public class UserController {
    
    private final UserServiceImpl service;

    public UserController(UserRepository repo, UserServiceImpl ser){

        this.service = ser;
    }

    @GetMapping("/sing_up")
    public String createAccount(String mail, String password, String login){

        UserEntity user = service.createUser(mail, password, login);
        return "sing_up_succesful";
    }
}
