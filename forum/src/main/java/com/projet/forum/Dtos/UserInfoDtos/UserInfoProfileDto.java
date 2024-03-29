package com.projet.forum.Dtos.UserInfoDtos;
import com.projet.forum.Entities.Status;
import com.projet.forum.Entities.Badge;
import com.projet.forum.Entities.Role;
import com.projet.forum.Dtos.MessageDtos.ListedMessageDto;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public record UserInfoProfileDto(

    Long id,
    String login,
    byte[] img,
    Status status,
    String bio,
    Date age,
    List<Badge> badges,
    int numberMessages,
    Role role,
    List<ListedMessageDto> messages
){
    public UserInfoProfileDto{

        Objects.requireNonNullElse(numberMessages, 0);
        Objects.requireNonNullElse(messages, null);
    }
}