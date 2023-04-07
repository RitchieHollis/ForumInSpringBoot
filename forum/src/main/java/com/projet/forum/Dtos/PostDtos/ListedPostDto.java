package com.projet.forum.Dtos.PostDtos;

import java.time.LocalDateTime;

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
{}
