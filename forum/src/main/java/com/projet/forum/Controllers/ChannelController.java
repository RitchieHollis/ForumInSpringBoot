package com.projet.forum.Controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

import com.projet.forum.Services.ChannelServices.*;
import com.projet.forum.Services.PostServices.PostService;
import com.projet.forum.Entities.ChannelEntity;
import com.projet.forum.Entities.Category;
import com.projet.forum.Dtos.ChannelDtos.*;
import com.projet.forum.Dtos.PostDtos.LatestPostDto;

@RestController()
@RequestMapping("/channels")
public class ChannelController {
    
    private final ChannelService service;
    private final PostService p_service;

    public ChannelController(ChannelService cs, PostService ps){ this.service = cs; this.p_service = ps;}
/* 
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ListedChannelDto>> showChannels(@RequestParam(name= "cat") Category c){

        List<ChannelEntity> channels = service.showAllChannelsOfCategory(c);

        return channels.stream().map(it -> new ListedChannelDto( //showLatestPost in PostService must be done first
            it.getTitle(), service.showNumberOfPosts(it.getId()), new LatestPostDto(p_service., null, null)))
    }*/
}
