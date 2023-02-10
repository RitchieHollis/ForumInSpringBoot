package com.projet.forum.Exceptions.ChannelExceptions;

import java.util.NoSuchElementException;

public class ChannelNotFoundException extends NoSuchElementException{
    
    private String message;

    public ChannelNotFoundException() {}

    public ChannelNotFoundException(String message){

        super(message);
        this.message = message;
    }
}
