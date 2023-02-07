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

@RestController()
@RequestMapping("/users_info")
public class UserInfoController {
    
    private final UserServiceImpl service;
    private final UserInfoServiceImpl info_service;

    public UserInfoController(UserServiceImpl ser, UserInfoServiceImpl u_ser){ this.service = ser; this.info_service = u_ser; }

    @GetMapping()
    public String showListOfUsers(Model model){

        List<UserEntity> listUsers = service.findAllUsers();
        List<Map<Map<String,Status>, byte[]>> listInfo = new ArrayList<>();

        for(UserEntity u : listUsers){
            listInfo.add(info_service.displayInfo(u.getId()));
        }
        model.addAttribute("userInfo", listInfo);
        return "/users_list";
    }
}
