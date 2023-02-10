package com.projet.forum.Dtos.PostDtos;

import java.time.LocalDateTime;

public record LatestPostDto( 

    String title,
    String user_login,
    LocalDateTime modified_at
){}

