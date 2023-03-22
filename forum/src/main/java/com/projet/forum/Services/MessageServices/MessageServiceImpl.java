package com.projet.forum.Services.MessageServices;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.forum.Entities.*;
import com.projet.forum.Repositories.*;
import com.projet.forum.Exceptions.MessageExceptions.*;

@Service
public class MessageServiceImpl implements MessageService{
    
    @Autowired

    private final MessageRepository repository;
    private final UserRepository u_repository;
    private final PostRepository p_repository;

    public MessageServiceImpl(MessageRepository mr, UserRepository ur, PostRepository pr){
        this.repository = mr;
        this.u_repository = ur;
        this.p_repository = pr;
    }

    /*@Override public void createMessage(String text, Long id_user, Long id_post){

        UserEntity user = u_repository.findById(id_user).get();
        PostEntity post = p_repository.findById(id_post).get();
        
        MessageEntity message = new MessageEntity();
        message.setPost(post);
        message.setUser_author(user);
        message.setContent(text);
        message.setCreated_at(LocalDateTime.now());
        message.setModified_at(message.getCreated_at());

        repository.save(message);
    }*/

    @Override public void modifyMessage(Long uId, Long id, String text){

        UserEntity user = u_repository.findById(uId).orElseThrow();
        MessageEntity message = repository.findById(id).orElseThrow();

        if(user.getId().equals(message.getUser_author().getId()) || user.getRole().equals(Role.ADMIN)){
            
            if(!(message.getContent().equals(text)))
                message.setContent(text);
            else throw new EqualMessageException("You can't change message to the same text");
        }
    }

    @Override public void deleteMessage(Long uId, Long id){

        UserEntity user = u_repository.findById(uId).orElseThrow();
        MessageEntity message = repository.findById(id).orElseThrow();

        if(user.getId().equals(message.getUser_author().getId()) || user.getRole().equals(Role.ADMIN))
        repository.delete(message);
    }
}
