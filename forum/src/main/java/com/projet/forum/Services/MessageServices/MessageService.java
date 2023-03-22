package com.projet.forum.Services.MessageServices;

public interface MessageService {

    //public abstract void createMessage(String text, Long id, Long id2);

    public abstract void modifyMessage(Long uId, Long id, String text);

    public abstract void deleteMessage(Long uId, Long id);
}
