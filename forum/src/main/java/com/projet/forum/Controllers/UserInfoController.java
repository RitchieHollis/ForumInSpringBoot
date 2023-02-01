package com.projet.forum.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.projet.forum.Services.UserServices.*;

@RestController
public class UserInfoController {
    
    private final UserServiceImpl service;

    public UserInfoController(UserServiceImpl ser){ this.service = ser; }

}
