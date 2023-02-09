package com.projet.forum.dtos.userInfos;
import com.projet.forum.Entities.Status;

public record UserInfoStateDto(
    String login,
    Status status,
    byte[] img
) {}