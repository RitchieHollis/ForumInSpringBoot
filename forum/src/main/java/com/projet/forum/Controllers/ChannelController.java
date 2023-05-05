package com.projet.forum.Controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin("http://localhost:4200")
public class ChannelController {
    
    private final ChannelService service;
    private final PostService p_service;

    public ChannelController(ChannelService cs, PostService ps){ this.service = cs; this.p_service = ps;}

    @GetMapping("/name")
    @ResponseBody
    public ResponseEntity<ChannelNameDto> showNameOfChannelById(@RequestParam(name= "id") Long id){

        ChannelEntity channel = service.getChannelById(id);
        ChannelNameDto dto = new ChannelNameDto(channel.getTitle());
        return ResponseEntity.ok(dto);
    } 

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ListedChannelDto>> showChannels(@RequestParam(name= "cat") Category c){

        List<ChannelEntity> channels = service.showAllChannelsOfCategory(c);

        return ResponseEntity.ok(channels.stream().map(


            it -> new ListedChannelDto( 

                it.getId(), 
                it.getTitle(), 
                service.showNumberOfPosts(it.getId()), 
                LatestPostDto.createDto(service.showLatestPostOfChannel(it.getId()), p_service.showLatestMessage(service.showLatestPostOfChannel(it.getId()).getId())),
                it.getCategory().toString()))
                .toList()
                );
    }

    @PostMapping("/createChannel")
    public ResponseEntity<ChannelEntity> createChannel(
        @RequestParam(name= "cat") Category c,
        @RequestParam(name="title") String text,
        Principal principal){

        ChannelEntity channel = service.createChannel(principal.getName(), text, c);
        return ResponseEntity.ok(channel);
    }
    @GetMapping("/posts")
    public ResponseEntity<List<ListedPostDto>> showListOfPosts(@RequestParam(name="channel") Long cId) {

        List<PostEntity> posts = service.showAllPosts(cId);
        System.out.println(posts.get(0).getTitle());
        
        return ResponseEntity.ok(posts.stream().map(
            post -> ListedPostDto.createDto(post, p_service.showAllMessages(post.getId()))
        ).toList());
    }
}
