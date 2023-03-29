package com.projet.forum.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet.forum.Services.ChannelServices.*;
import com.projet.forum.Services.PostServices.PostService;
import com.projet.forum.Entities.ChannelEntity;
import com.projet.forum.Entities.PostEntity;
import com.projet.forum.Entities.Category;
import com.projet.forum.Dtos.ChannelDtos.*;
import com.projet.forum.Dtos.PostDtos.*;

@RestController
@RequestMapping("/channels")
public class ChannelController {
    
    private final ChannelService service;
    private final PostService p_service;

    public ChannelController(ChannelService cs, PostService ps){ this.service = cs; this.p_service = ps;}

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ListedChannelDto>> showChannels(@RequestParam(name= "cat") Category c){

        List<ChannelEntity> channels = service.showAllChannelsOfCategory(c);

        return ResponseEntity.ok(channels.stream().map(

            it -> new ListedChannelDto( 

                it.getTitle(), service.showNumberOfPosts(it.getId()), new LatestPostDto(

                    service.showLatestPostOfChannel(it.getId()).getTitle(), 
                    p_service.showLatestMessage(service.showLatestPostOfChannel(it.getId()).getId()).
                        getUser_author().getUser_info().getLogin(), 
                    service.showLatestPostOfChannel(it.getId()).getModified_at()))).toList()
                );
    }

    @PostMapping("/createChannel")
    public ResponseEntity<ChannelEntity> createChannel(
        @RequestParam(name= "cat") Category c,
        @RequestParam(name = "id") Long id,
        @RequestParam(name="title") String text){

        ChannelEntity channel = service.createChannel(id, text, c);
        return ResponseEntity.ok(channel);
    }
    @GetMapping("/posts")
    public ResponseEntity<List<ListedPostDto>> showListOfPosts(@RequestParam(name="channel") Long cId) {

        List<PostEntity> posts = service.showAllPosts(cId);

        return ResponseEntity.ok(posts.stream().map(
            post -> new ListedPostDto(
                post.getTitle(), 
                post.getMessages().get(0).getUser_author().getUser_info().getLogin(),
                post.getCreated_at(),
                post.getMessages().size(),
                post.getNb_views(),
                post.getMessages().get(post.getMessages().size()-1).getUser_author().getUser_info().getLogin(),
                post.getModified_at()
            )
        ).toList());
    }
}
