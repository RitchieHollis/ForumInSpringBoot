package com.projet.forum.Dtos.UserDtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record UserConnectionDto(

/* 
    @NotNull
    @NotEmpty
    String mail,
*/
    @NotNull
    @NotEmpty
    String password,
    
     
    @NotNull
    @NotEmpty
    String login
    
) 
{}
