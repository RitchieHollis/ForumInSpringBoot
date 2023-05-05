package com.projet.forum.Dtos.PostDtos;

import java.time.LocalDateTime;
import java.util.List;

import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.PostEntity;



public record ListedPostDto(

    Long id,
    String title,
    String op_login,
    Long op_id,
    LocalDateTime created_at,
    int numberMessages,
    int numberOfViews,
    String last_user_login,
    Long last_user_id,
    String last_message_content,
    LocalDateTime modified_at
)
{

    public static ListedPostDto createDto(PostEntity post, List<MessageEntity> listMessages){

        MessageEntity firstMessage = listMessages.get(0);
        MessageEntity lastMessage = listMessages.get(listMessages.size()-1);

        ListedPostDto dto = new ListedPostDto(post.getId(), 
                                              post.getTitle(), 
                                              firstMessage.getUser_author().getUser_info().getLogin(), 
                                              firstMessage.getUser_author().getId(), 
                                              post.getCreated_at(), 
                                              listMessages.size(), 
                                              post.getNb_views(), 
                                              lastMessage.getUser_author().getUser_info().getLogin(), 
                                              lastMessage.getUser_author().getId(), 
                                              lastMessage.getContent(), 
                                              post.getModified_at());
        return dto;
    }
}
