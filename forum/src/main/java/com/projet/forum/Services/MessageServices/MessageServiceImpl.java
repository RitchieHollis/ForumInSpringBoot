package com.projet.forum.Services.MessageServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.forum.Entities.*;
import com.projet.forum.Repositories.*;
import com.projet.forum.Exceptions.MessageExceptions.*;

@Service
public class MessageServiceImpl implements MessageService{
    
    @Autowired

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

    @Override public void modifyMessage(Long id, String text){

        MessageEntity message = repository.findById(id).orElseThrow();
        
        if(!(message.getContent().equals(text)))
            message.setContent(text);
        else throw new EqualMessageException("You can't change message to the same text");
    }

    @Override public void deleteMessage(Long id){

        MessageEntity message = repository.findById(id).orElseThrow();
        repository.delete(message);
    }
}
