package com.projet.forum.Services.ChannelServices;

import com.projet.forum.Entities.Category;
import com.projet.forum.Entities.PostEntity;
import com.projet.forum.Entities.ChannelEntity;

import java.util.List;

public interface ChannelService {

    public abstract ChannelEntity createChannel(String username, String text, Category c);

    public abstract void archiviseChannel(Long id, Long id_user);

    public abstract ChannelEntity getChannelById(Long id);

    public abstract List<ChannelEntity> showAllChannelsOfCategory(Category category);

    public abstract void addPost(Long id, Long id_user, String title, String text);

    public abstract List<PostEntity> showAllPosts(Long id);

    public abstract int showNumberOfPosts(Long id);

    public abstract PostEntity showLatestPostOfChannel(Long id);
}
