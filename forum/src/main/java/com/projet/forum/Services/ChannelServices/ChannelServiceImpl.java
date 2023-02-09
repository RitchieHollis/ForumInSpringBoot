package com.projet.forum.Services.ChannelServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.forum.Entities.Category;
import com.projet.forum.Entities.ChannelEntity;
import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.PostEntity;
import com.projet.forum.Entities.Role;
import com.projet.forum.Repositories.ChannelRepository;
import com.projet.forum.Repositories.MessageRepository;
import com.projet.forum.Repositories.PostRepository;
import com.projet.forum.Repositories.UserRepository;
import com.projet.forum.Exceptions.*;
import com.projet.forum.Exceptions.UserExceptions.InexistantUserException;

@Service
public class ChannelServiceImpl implements ChannelService{
    
    @Autowired

    private final UserRepository u_repository;
    private final ChannelRepository repository;
    private final PostRepository p_repository;
    private final MessageRepository m_repository;

    public ChannelServiceImpl(ChannelRepository cr, UserRepository ur, PostRepository pr, MessageRepository mr){

        this.repository = cr;
        this.u_repository = ur;
        this.p_repository = pr;
        this.m_repository = mr;
    }

    @Override public void createChannel(Long id_user, String text, Category category){

        if(u_repository.findById(id_user).isEmpty())
            throw new InexistantUserException("User not found");

        if(u_repository.findById(id_user).get().getRole() != Role.ADMIN)
            throw new UserNotAllowedException("You don't have a specific role to execute this action");
        else{

            ChannelEntity channel = new ChannelEntity();
            channel.setTitle(text);
            channel.setCategory(category);
            repository.save(channel);
        }
    }

    @Override public void archiviseChannel(Long id, Long id_user){

        ChannelEntity channel = repository.findById(id).orElseThrow();

        if(u_repository.findById(id_user).isEmpty())
            throw new InexistantUserException("User not found");

        if(u_repository.findById(id_user).get().getRole() != Role.ADMIN)
            throw new UserNotAllowedException("You don't have a specific role to execute this action");
        else{

            repository.delete(channel);
        }
    }

    @Override public void addPost(Long id, Long id_user, String title, String text){

        if(u_repository.findById(id_user).isEmpty())
            throw new InexistantUserException("User not found");
        
        PostEntity post = new PostEntity();
        MessageEntity message = new MessageEntity();

        post.setChannel(repository.findById(id).get());
        post.setTitle(title);
        post.setNb_views(0L);
        p_repository.save(post);

        message.setUser_author(u_repository.findById(id_user).get());
        message.setContent(text);
        message.setPost(post);
        m_repository.save(message);
    }
}
