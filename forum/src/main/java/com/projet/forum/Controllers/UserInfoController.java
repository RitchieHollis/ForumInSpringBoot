package com.projet.forum.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.projet.forum.Services.UserServices.*;
import com.projet.forum.Services.UserInfoServices.*;
import com.projet.forum.Services.MessageServices.*;
import com.projet.forum.Dtos.UserInfoDtos.UserInfoProfileDto;
import com.projet.forum.Dtos.UserInfoDtos.UserInfoStateDto;
import com.projet.forum.Dtos.MessageDtos.ListedMessageDto;
import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Entities.MessageEntity;

import java.util.List;

@RestController()
@RequestMapping("/users_info")
public class UserInfoController {
    
    private final UserServiceImpl service;
    private final UserInfoServiceImpl info_service;
    private final MessageServiceImpl m_service;

    public UserInfoController(UserServiceImpl ser, UserInfoServiceImpl u_ser, MessageServiceImpl m_ser) {
        
        this.service = ser; this.info_service = u_ser; this.m_service = m_ser; 
    }

    @GetMapping("/users_list")
    @ResponseBody
    public List<UserInfoStateDto> showStatesOfUsers(){

        List<UserEntity> listUsers = service.findAllUsers();
        
        return listUsers.stream().map(it -> new UserInfoStateDto(
            it.getUser_info().getLogin(),
            it.getUser_info().getStatus(),
            it.getUser_info().getProfile_picture(),
            it.getUser_info().getBadges().get(it.getUser_info().getBadges().size()-1))).toList(); //last badge obtained
    }

    @GetMapping("/userProfile")
    @ResponseBody
    public ResponseEntity<UserInfoProfileDto> showProfileOfUser(@RequestParam Long id){

        UserEntity user = service.findUserById(id).orElseThrow();
        int nbreMessages = service.findTotalMessagesOfUser(id);

        List<MessageEntity> messages = service.findAllMessagesOfUser(id);

        List<ListedMessageDto> m = messages.stream().map(message -> new ListedMessageDto(
            message.getPost().getTitle(),
            message.getPost().getChannel().getTitle(),
            message.getContent(),
            message.getCreated_at(),
            message.getPost().getMessages().size()-1
        )).toList();

        UserInfoProfileDto dto = new UserInfoProfileDto(user.getUser_info().getLogin(),
                                      user.getUser_info().getProfile_picture(),
                                      user.getUser_info().getStatus(),
                                      user.getUser_info().getBio(),
                                      user.getUser_info().getAge(), 
                                      user.getUser_info().getBadges(),
                                      (int)nbreMessages,
                                      user.getRole(),
                                      m);
        return ResponseEntity.ok(dto);
    }
}
