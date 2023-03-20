package com.projet.forum.Dtos.PostDtos;

import java.time.LocalDateTime;

public record ListedPostDto(

    String tittle,
    String op_login,
    LocalDateTime created_at,
    int numberMessages,
    int numberOfViews,
    String last_user_login,
    LocalDateTime modified_at
)
{}
