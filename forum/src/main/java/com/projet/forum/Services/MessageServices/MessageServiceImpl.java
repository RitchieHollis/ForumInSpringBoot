package com.projet.forum.Services.MessageServices;

import org.springframework.stereotype.Service;
import com.projet.forum.Entities.*;
import com.projet.forum.Repositories.*;

@Service
public class MessageServiceImpl implements MessageService{
    
    private MessageRepository repository;
    private UserRepository u_repository;
    private PostRepository p_repository;

    @Override public void createMessage(String text, Long id_user, Long id_post){

        UserEntity user = u_repository.findById(id_user).get();
        PostEntity post = p_repository.findById(id_post).get();
        
        MessageEntity message = new MessageEntity();
        message.setPost(post);
        message.setUser_author(user);
        message.setContent(text);

        repository.save(message);
    }
}
