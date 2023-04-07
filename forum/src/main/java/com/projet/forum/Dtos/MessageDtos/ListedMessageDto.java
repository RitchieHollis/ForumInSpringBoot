package com.projet.forum.Dtos.MessageDtos;

import java.time.LocalDateTime;
import java.util.Objects;

public record ListedMessageDto(

    Long id,
    String post_name,
    String channel_name,
    String content,
    LocalDateTime created_at,
    int numberMessagesInPost
)
{
    public ListedMessageDto{
        Objects.requireNonNullElse(numberMessagesInPost, 0);
    }
}
