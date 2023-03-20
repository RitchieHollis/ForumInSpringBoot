package com.projet.forum.Dtos.MessageDtos;

import java.time.LocalDateTime;
import java.util.Objects;

public record ShowMessageDto(

    String userLogin,
    LocalDateTime created_at,
    LocalDateTime modified_at,
    String citation,
    String content

) 
{
    public ShowMessageDto{

        Objects.requireNonNullElse(modified_at, null);
        Objects.requireNonNullElse(citation, null);
    }
}
