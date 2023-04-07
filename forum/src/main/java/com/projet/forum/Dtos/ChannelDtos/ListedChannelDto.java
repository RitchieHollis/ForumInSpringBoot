package com.projet.forum.Dtos.ChannelDtos;

import com.projet.forum.Dtos.PostDtos.LatestPostDto;

public record ListedChannelDto(

    Long id,
    String title,
    int numberPosts,
    LatestPostDto latestPost,
    String category
) {}
