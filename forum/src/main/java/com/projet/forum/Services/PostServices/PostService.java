package com.projet.forum.Services.PostServices;

import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Entities.PostEntity;

import java.util.List;

public interface PostService {
    
    public abstract MessageEntity showLatestMessage(Long id);

    public abstract List<MessageEntity> showAllMessages(Long id);

    public abstract void archivisePost(Long id);

    public abstract void addFirstMessage(PostEntity post, UserEntity user,String content);

    public abstract PostEntity createPost(String username, Long id_channel, String title, String text);
}
