package com.projet.forum.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.ChannelEntity;
import com.projet.forum.Entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity,Long>{
    
    @Query("SELECT u.posts FROM ChannelEntity u")
    List<PostEntity> findAllPosts(Long id);

    @Query("SELECT u.created_at, u.modified_at FROM ChannelEntity u WHERE u.id = :id")
    List<LocalDate> displayTimeInfo(@Param("id") Long id);
}