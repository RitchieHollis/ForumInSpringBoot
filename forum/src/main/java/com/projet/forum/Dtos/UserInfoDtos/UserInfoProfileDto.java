package com.projet.forum.Dtos.UserInfoDtos;
import com.projet.forum.Entities.Status;
import com.projet.forum.Entities.Badge;
import java.sql.Date;
import java.util.List;

public record UserInfoProfileDto(

    String login,
    byte[] img,
    Status status,
    String bio,
    Date age,
    List<Badge> badges,
    int numberMessages
){}