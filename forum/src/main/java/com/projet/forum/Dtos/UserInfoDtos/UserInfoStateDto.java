package com.projet.forum.Dtos.UserInfoDtos;
import com.projet.forum.Entities.Status;

public record UserInfoStateDto(
    
    String login,
    Status status,
    byte[] img
) {}