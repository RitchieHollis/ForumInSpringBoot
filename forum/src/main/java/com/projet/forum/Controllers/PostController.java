package com.projet.forum.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.projet.forum.Services.PostServices.*;
import com.projet.forum.Entities.PostEntity;

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

}
