package com.projet.forum.Services.PostServices;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.PostEntity;
import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Repositories.PostRepository;
import com.projet.forum.Repositories.MessageRepository;

@Service
public class PostServiceImpl implements PostService{
    @Autowired

    private final PostRepository repository; 
    private final MessageRepository m_repository;

    public PostServiceImpl(PostRepository pr, MessageRepository mr){
        this.repository = pr;
        this.m_repository = mr;
    }

    @Override public MessageEntity showLatestMessage(Long id){

        PostEntity post = repository.findById(id).orElseThrow();

        int i = post.getMessages().size()-1;
        while(i >= 0){

            if(post.getMessages().get(i).isArchived())
                i--;
            else {

                MessageEntity latestMessage = post.getMessages().get(i);
                return latestMessage;
            }
        }
        return null;
    }

    @Override public void archivisePost(Long id){

        PostEntity post = repository.findById(id).orElseThrow();
        post.setArchived(true);

        for(MessageEntity m : post.getMessages()){

            m.setArchived(true);
            m.setModified_at(LocalDateTime.now());
        }
        
        post.setModified_at(LocalDateTime.now());
        repository.save(post);
    }

    @Override public void addMessage(Long id, UserEntity user, String content){

        PostEntity post = repository.findById(id).orElseThrow();

        MessageEntity message = new MessageEntity();
        message.setCreated_at(LocalDateTime.now());
        message.setModified_at(message.getCreated_at()); //Question - peut-on faire comme cela? 
        message.setPost(post);                          //Ou plûtot faire addMessage dans MessageService
        message.setUser_author(user);
        message.setContent(content);

        m_repository.saveAndFlush(message);
        repository.save(post);
    }
}
