package com.projet.forum.Dtos.PostDtos;

import java.time.LocalDateTime;

import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.PostEntity;

public record LatestPostDto( 

    Long id,
    String title,
    String user_login,
    Long user_id,
    LocalDateTime modified_at
)
{
    public static LatestPostDto createDto(PostEntity latestPost, MessageEntity latestMessageOfPost){

        LatestPostDto dto = new LatestPostDto(latestPost.getId(), 
                                              latestPost.getTitle(), 
                                              latestMessageOfPost.getUser_author().getUser_info().getLogin(),
                                              latestMessageOfPost.getUser_author().getId(),
                                              latestPost.getModified_at());
        
        return dto;
    }
}

