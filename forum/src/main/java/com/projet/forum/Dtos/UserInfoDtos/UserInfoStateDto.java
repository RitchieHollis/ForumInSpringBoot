package com.projet.forum.Dtos.UserInfoDtos;

import com.projet.forum.Entities.Role;
import com.projet.forum.Entities.Status;

public record UserInfoStateDto(
    
    Long id,
    String login,
    Status status,
    byte[] img,
    Role role
) {}