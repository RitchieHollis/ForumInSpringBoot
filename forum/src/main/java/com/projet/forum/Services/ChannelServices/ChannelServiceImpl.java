package com.projet.forum.Services.ChannelServices;

import java.time.LocalDateTime;
import java.util.List;

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
import com.projet.forum.Exceptions.ChannelExceptions.ChannelNotFoundException;
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

    @Override public ChannelEntity createChannel(Long id_user, String text, Category category){

        if(u_repository.findById(id_user).isEmpty())
            throw new InexistantUserException("User not found");

        if(!(u_repository.findById(id_user).get().getRole().equals(Role.ADMIN)))
            throw new UserNotAllowedException("You don't have a specific role to execute this action");
        else{

            ChannelEntity channel = new ChannelEntity();
            channel.setTitle(text);
            channel.setCategory(category);
            channel.setCreated_at(LocalDateTime.now());
            repository.save(channel);
            return channel;
        }
    }

    @Override public void archiviseChannel(Long id, Long id_user){

        ChannelEntity channel = repository.findById(id).orElseThrow();

        if(u_repository.findById(id_user).isEmpty())
            throw new InexistantUserException("User not found");

        if(u_repository.findById(id_user).get().getRole() != Role.ADMIN)
            throw new UserNotAllowedException("You don't have a specific role to execute this action");
        else{

            for(PostEntity p : channel.getPosts()){

                p.setArchived(true);
                p_repository.saveAndFlush(p);
            }

            channel.setArchived(true);
            repository.save(channel);
        }
    }

    @Override public List<ChannelEntity> showAllChannelsOfCategory(Category category){

        for(Category c : Category.values()){

            if(category.name().equals(c.name())){

                List<ChannelEntity> channels = repository.showAllChannelsInCategory(category);
                return channels;
            }
        }
        return null;
    }

    @Override public void addPost(Long id, Long id_user, String title, String text){

        if(u_repository.findById(id_user).isEmpty())
            throw new InexistantUserException("User not found");
        
        PostEntity post = new PostEntity();
        MessageEntity message = new MessageEntity();

        ChannelEntity c = repository.findById(id).get();

        post.setChannel(c);
        post.setTitle(title);
        post.setNb_views(0);
        post.setCreated_at(LocalDateTime.now());
        post.setModified_at(post.getCreated_at());
        p_repository.save(post);

        message.setUser_author(u_repository.findById(id_user).get());
        message.setContent(text);
        message.setPost(post);
        message.setCreated_at(LocalDateTime.now());
        message.setModified_at(message.getCreated_at());
        m_repository.save(message);

        c.setModified_at(LocalDateTime.now());
        repository.saveAndFlush(c);
    }

    @Override public List<PostEntity> showAllPosts(Long id){

        if(repository.findById(id).isPresent())
            return p_repository.findAllPosts(id);
        else throw new ChannelNotFoundException("Channel not found");
    }

    @Override public int showNumberOfPosts(Long id){

        ChannelEntity channel = repository.findById(id).orElseThrow();

        if(channel.isArchived()) throw new ChannelNotFoundException("Channel not found");

        List<PostEntity> posts_list = p_repository.findAllPosts(id);
        if(posts_list.isEmpty())
            return 0;
        else return posts_list.size();
    }

    @Override public PostEntity showLatestPostOfChannel(Long id){
        
        ChannelEntity channel = repository.findById(id).orElseThrow();

        if(channel.isArchived()) throw new ChannelNotFoundException("Channel not found");

        return p_repository.findLatestPostOfChannel(channel.getId());
    }
}
