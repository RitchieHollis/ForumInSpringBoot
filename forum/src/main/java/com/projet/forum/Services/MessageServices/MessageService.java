package com.projet.forum.Services.MessageServices;

public interface MessageService {

    public abstract void createMessage(String text, String username, Long id);

    public abstract void modifyMessage(String username, Long id, String text);

    public abstract void deleteMessage(String username, Long id);
}
