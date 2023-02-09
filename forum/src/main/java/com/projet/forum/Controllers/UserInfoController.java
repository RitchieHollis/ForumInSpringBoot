package com.projet.forum.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet.forum.Services.UserServices.*;
import com.projet.forum.Services.UserInfoServices.*;
import com.projet.forum.Entities.Status;
import com.projet.forum.Entities.UserEntity;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.projet.forum.dtos.userInfos.UserInfoStateDto;

@RestController()
@RequestMapping("/users_info")
public class UserInfoController {
    
    private final UserServiceImpl service;
    private final UserInfoServiceImpl info_service;

    public UserInfoController(UserServiceImpl ser, UserInfoServiceImpl u_ser){ this.service = ser; this.info_service = u_ser; }

    @GetMapping("/users_list")
    @ResponseBody
    public List<UserInfoStateDto> showStatesOfUsers(){

        List<UserEntity> listUsers = service.findAllUsers();
        
        return listUsers.stream().map(it -> new UserInfoStateDto(
            it.getUser_info().getLogin(),
            it.getUser_info().getStatus(),
            it.getUser_info().getProfile_picture())).toList();
    }
}
