package com.projet.forum.Services.PostServices;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.PostEntity;
import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Entities.ChannelEntity;
import com.projet.forum.Repositories.PostRepository;
import com.projet.forum.Repositories.MessageRepository;
import com.projet.forum.Repositories.UserRepository;
import com.projet.forum.Repositories.ChannelRepository;

@Service
public class PostServiceImpl implements PostService{
    @Autowired

    private final PostRepository repository; 
    private final MessageRepository m_repository;
    private final UserRepository u_repository;
    private final ChannelRepository c_repository;

    public PostServiceImpl(PostRepository pr, MessageRepository mr, UserRepository ur, ChannelRepository cr){
        this.repository = pr;
        this.m_repository = mr;
        this.u_repository = ur;
        this.c_repository = cr;
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

    @Override public PostEntity createPost(Long user_id, Long channel_id, String title, String content){

        UserEntity user = u_repository.findById(user_id).orElseThrow();
        ChannelEntity channel = c_repository.findById(channel_id).orElseThrow();

        PostEntity post = new PostEntity();
        post.setCreated_at(LocalDateTime.now());
        post.setModified_at(post.getCreated_at());
        post.setChannel(channel);
        post.setTitle(title);
        this.addMessage(post.getId(), user, content);

        repository.saveAndFlush(post);
        return post;
    }
}
