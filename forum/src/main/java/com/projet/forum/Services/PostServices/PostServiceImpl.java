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

import java.util.List;

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
        List<MessageEntity> messages = m_repository.findAllMessagesByPostId(id);

        int i = messages.size()-1;
        while(i >= 0){

            if(messages.get(i).isArchived())
                i--;
            else {

                MessageEntity latestMessage = messages.get(i);
                return latestMessage;
            }
        }
        return null;
    }

    @Override public List<MessageEntity> showAllMessages(Long id){

        List<MessageEntity> messages = m_repository.findAllMessagesByPostId(id);
        return messages;
    }

    @Override public void archivisePost(Long id){

        PostEntity post = repository.findById(id).orElseThrow();
        post.setArchived(true);

        List<MessageEntity> messages = m_repository.findAllMessagesByPostId(id);

        for(MessageEntity m : messages){

            m.setArchived(true);
            m.setModified_at(LocalDateTime.now());
        }
        
        post.setModified_at(LocalDateTime.now());
        repository.save(post);
    }
 
    @Override public void addFirstMessage(PostEntity post, UserEntity user, String content){

        MessageEntity message = new MessageEntity();
        message.setCreated_at(LocalDateTime.now());
        message.setModified_at(message.getCreated_at()); //Question - peut-on faire comme cela? 
        message.setPost(post);                          //Ou plûtot faire addMessage dans MessageService
        message.setUser_author(user);
        message.setContent(content);

        m_repository.saveAndFlush(message);
        repository.save(post);
    }

    @Override public PostEntity createPost(String username, Long channel_id, String title, String content){

        UserEntity user = u_repository.findByUsername(username).orElseThrow();
        ChannelEntity channel = c_repository.findById(channel_id).orElseThrow();

        if(user != null && channel != null){

            PostEntity post = new PostEntity();
            post.setCreated_at(LocalDateTime.now());
            post.setModified_at(post.getCreated_at());
            post.setChannel(channel);
            post.setTitle(title);
            post.setNb_views(0);
            repository.save(post);

            addFirstMessage(post, user, content);

            c_repository.saveAndFlush(channel);
            repository.saveAndFlush(post);
            return post;
        }
        else return null;
    }
}
