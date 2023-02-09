package com.projet.forum.Services.ChannelServices;

import com.projet.forum.Entities.Category;

public interface ChannelService {
    
    public abstract void createChannel(Long id_user, String text, Category c);

    public abstract void archiviseChannel(Long id, Long id_user);

    public abstract void addPost(Long id, Long id_user, String title, String text);
}
