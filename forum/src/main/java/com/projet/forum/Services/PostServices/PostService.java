package com.projet.forum.Services.PostServices;

import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.UserEntity;

public interface PostService {
    
    public abstract MessageEntity showLatestMessage(Long id);

    public abstract void archivisePost(Long id);

    public abstract void addMessage(Long id, UserEntity user,String content);
}
