package com.projet.forum.Exceptions.UserExceptions;

public class InexistantUserException extends RuntimeException{
    
    private String message;

    public InexistantUserException() {}

    public InexistantUserException(String message){

        super(message);
        this.message = message;
    }
}

