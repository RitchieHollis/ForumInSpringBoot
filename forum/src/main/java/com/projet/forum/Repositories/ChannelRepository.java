package com.projet.forum.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.ChannelEntity;
import com.projet.forum.Entities.PostEntity;

@Repository
public interface ChannelRepository extends CrudRepository<ChannelEntity,Long>{
    
    @Query("SELECT posts FROM ChannelEntity")
    List<PostEntity> findAllPosts(Long id);

    @Query("SELECT created_at, modified_at FROM ChannelEntity WHERE channel_id = :id")
    Map<LocalDate, LocalDate> displayTimeInfo(@Param("id") Long id);
}