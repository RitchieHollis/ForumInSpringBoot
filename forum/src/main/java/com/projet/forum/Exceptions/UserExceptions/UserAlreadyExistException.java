package com.projet.forum.Exceptions.UserExceptions;

public class UserAlreadyExistException extends RuntimeException{
    
    private String message;

    public UserAlreadyExistException() {}

    public UserAlreadyExistException(String message){

        super(message);
        this.message = message;
    }
}

