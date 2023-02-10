package com.projet.forum.Dtos.ChannelDtos;

import com.projet.forum.Dtos.PostDtos.LatestPostDto;

public record ListedChannelDto(

    String title,
    int numberPosts,
    LatestPostDto latestPost
) {}
