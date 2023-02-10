package com.projet.forum.Exceptions.UserExceptions;

import java.util.NoSuchElementException;

public class UserArchivisedException extends NoSuchElementException{
    
    private String message;

    public UserArchivisedException() {}

    public UserArchivisedException(String message){

        super(message);
        this.message = message;
    }
}
