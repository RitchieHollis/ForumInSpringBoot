package com.projet.forum.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.forum.Dtos.MessageDtos.ModifiedMessageDto;
import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Services.MessageServices.MessageServiceImpl;
import com.projet.forum.Services.UserServices.UserServiceImpl;

@RestController
@RequestMapping("/message")
public class MessageController {
    
    private final MessageServiceImpl service;
    private final UserServiceImpl u_service;

    @Autowired
    public MessageController(MessageServiceImpl ser, UserServiceImpl u_ser){
        this.service = ser;
        this.u_service = u_ser;
    }

    @PatchMapping()
    public ResponseEntity<Long> modifyMessage(@RequestParam(value="id") Long id, 
    @RequestBody() ModifiedMessageDto modifyMessageForm, Principal principal){

        service.modifyMessage(principal.getName(), id, modifyMessageForm.content());
        return ResponseEntity.ok(id);
    }

    @PatchMapping()
    public ResponseEntity<String> deleteMessage(@RequestParam(value="id") Long id, Principal principal){

        service.deleteMessage(principal.getName(), id);
        return ResponseEntity.ok("Message deleted");
    }
}
