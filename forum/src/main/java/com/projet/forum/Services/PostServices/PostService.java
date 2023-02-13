package com.projet.forum.Services.PostServices;

import com.projet.forum.Entities.MessageEntity;

public interface PostService {
    
    public abstract MessageEntity showLatestMessage(Long id);
}
