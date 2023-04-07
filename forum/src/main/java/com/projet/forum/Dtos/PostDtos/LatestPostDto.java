package com.projet.forum.Dtos.PostDtos;

import java.time.LocalDateTime;

public record LatestPostDto( 

    Long id,
    String title,
    String user_login,
    Long user_id,
    LocalDateTime modified_at
){}

