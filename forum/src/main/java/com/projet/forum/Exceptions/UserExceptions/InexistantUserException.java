package com.projet.forum.Exceptions.UserExceptions;

import java.util.NoSuchElementException;

public class InexistantUserException extends NoSuchElementException{
    
    private String message;

    public InexistantUserException() {}

    public InexistantUserException(String message){

        super(message);
        this.message = message;
    }
}

