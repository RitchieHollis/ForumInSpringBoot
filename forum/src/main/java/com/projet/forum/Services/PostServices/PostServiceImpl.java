package com.projet.forum.Services.PostServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.PostEntity;
import com.projet.forum.Repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService{
    @Autowired

    private final PostRepository repository; 

    public PostServiceImpl(PostRepository pr){
        this.repository = pr;
    }

    @Override public MessageEntity showLatestMessage(Long id){

        PostEntity post = repository.findById(id).orElseThrow();

        MessageEntity latestMessage = post.getMessages().get(post.getMessages().size()-1);

        return latestMessage;
    }
}
