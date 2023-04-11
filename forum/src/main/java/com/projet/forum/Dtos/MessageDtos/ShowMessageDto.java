package com.projet.forum.Dtos.MessageDtos;

import java.time.LocalDateTime;
import java.util.Objects;

import com.projet.forum.Dtos.UserInfoDtos.UserInfoStateDto;

public record ShowMessageDto(

    Long id,
    String userLogin,
    Long userId,
    LocalDateTime created_at,
    LocalDateTime modified_at,
    //String citation,
    String content,
    UserInfoStateDto userState

) 
{
    public ShowMessageDto{

        Objects.requireNonNullElse(modified_at, null);
        //Objects.requireNonNullElse(citation, null);
    }
}
