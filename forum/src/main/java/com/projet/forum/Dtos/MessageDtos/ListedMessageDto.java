package com.projet.forum.Dtos.MessageDtos;

import java.time.LocalDateTime;

public record ListedMessageDto(

    String post_name,
    String channel_name,
    String content,
    LocalDateTime created_at,
    int numberMessagesInPost
)
{}
