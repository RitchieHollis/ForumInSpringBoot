package com.projet.forum.Exceptions.MessageExceptions;

public class EqualMessageException extends RuntimeException{
    
    private String message;

    public EqualMessageException() {}

    public EqualMessageException(String message){

        super(message);
        this.message = message;
    }
}
