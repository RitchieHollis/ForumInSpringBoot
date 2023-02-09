package com.projet.forum.Exceptions;

public class UserNotAllowedException extends RuntimeException{
    
    private String message;

    public UserNotAllowedException() {}

    public UserNotAllowedException(String message){

        super(message);
        this.message = message;
    }
}
