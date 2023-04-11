package com.projet.forum.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.projet.forum.Services.PostServices.*;
import com.projet.forum.Services.UserInfoServices.UserInfoService;
import com.projet.forum.Services.UserInfoServices.UserInfoServiceImpl;
import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.PostEntity;
import com.projet.forum.Dtos.MessageDtos.*;
import com.projet.forum.Dtos.PostDtos.PostNameDto;
import com.projet.forum.Dtos.UserInfoDtos.UserInfoStateDto;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin("http://localhost:4200")
public class PostController {
    
    private final PostServiceImpl service;
    private final UserInfoServiceImpl u_i_service;

    public PostController(PostServiceImpl ser, UserInfoServiceImpl ui_ser){

        this.service = ser; this.u_i_service = ui_ser;
    }

    @PostMapping("/createPost")
    public ResponseEntity<PostEntity> createPost(
        @RequestParam(name="channelId") Long cId,
        @RequestParam(name="title") String title,
        @RequestParam(name="content") String content,
        Principal principal
    ){

        PostEntity post = service.createPost(principal.getName(), cId, title, content);
        return ResponseEntity.ok(post);
    }

    @GetMapping()
    public ResponseEntity<List<ShowMessageDto>> showAllMessages(@RequestParam(name="id") Long id){

        List<MessageEntity> messages = service.showAllMessages(id);
        UserInfoStateDto dto;

        return ResponseEntity.ok(messages.stream().map(
            message -> new ShowMessageDto(
                message.getId(),
                message.getUser_author().getUser_info().getLogin(),
                message.getUser_author().getId(),
                message.getCreated_at(), 
                message.getModified_at(), 
                //null, 
                message.getContent(),
                u_i_service.showUserInHover(id)
            )
        ).toList());
    }

    @GetMapping("/getName")
    public ResponseEntity<PostNameDto> getNameById(@RequestParam(name = "id") Long id){

        String name = service.getPostNameById(id);
        PostNameDto dto = new PostNameDto(name);
        return ResponseEntity.ok(dto);
    }
}
