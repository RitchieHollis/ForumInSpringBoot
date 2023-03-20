package com.projet.forum.Dtos.UserInfoDtos;

import com.projet.forum.Entities.Status;
import com.projet.forum.Entities.Badge;
public record UserInfoStateDto(
    
    String login,
    Status status,
    byte[] img,
    Badge badge
) {}