package com.projet.forum.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.projet.forum.Services.PostServices.*;
import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.PostEntity;
import com.projet.forum.Dtos.MessageDtos.*;

import java.util.List;

@RestController()
@RequestMapping("/post")
public class PostController {
    
    private final PostServiceImpl service;

    public PostController(PostServiceImpl ser){

        this.service = ser;
    }

    @PostMapping("/createPost")
    public ResponseEntity<PostEntity> createPost(
        @RequestParam(name="channelId") Long cId,
        @RequestParam(name="title") String title,
        @RequestParam(name="userId") Long uId,
        @RequestParam(name="content") String content
    ){

        PostEntity post = service.createPost(uId, cId, title, content);
        return ResponseEntity.ok(post);
    }

    @GetMapping()
    public ResponseEntity<List<ShowMessageDto>> showAllMessages(@RequestParam(name="id") Long id){

        List<MessageEntity> messages = service.showAllMessages(id);

        return ResponseEntity.ok(messages.stream().map(
            message -> new ShowMessageDto(
                message.getUser_author().getUser_info().getLogin(),
                message.getCreated_at(), 
                message.getModified_at(), 
                null, 
                message.getContent()
            )
        ).toList());
    }
}
