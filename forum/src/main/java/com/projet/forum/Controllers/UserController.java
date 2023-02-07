package com.projet.forum.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Repositories.UserRepository;
import com.projet.forum.Services.UserServices.UserServiceImpl;

@RestController("/sing_up")
public class UserController {
    
    private final UserServiceImpl service;

    public UserController(UserRepository repo, UserServiceImpl ser){

        this.service = ser;
    }

    @PostMapping(value = "/sing_up_succesful")
    public UserEntity createAccount(String mail, String password, String login){

        UserEntity user = service.createUser(mail, password, login);
        return service.saveUser(user);
    }
}
