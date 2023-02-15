package com.projet.forum.Repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.PostEntity;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long>{
    
    @Query("SELECT u messages FROM PostEntity u")
    public List<MessageEntity> findAllMessages();

    @Query("SELECT u.created_at, u.modified_at FROM PostEntity u WHERE u.id = :id")
    List<LocalDate> displayTimeInfo(@Param("id") Long id);

    // @Query("SELECT u.posts FROM PostEntity JOIN u.channel WHERE a.id = :id AND u.archived = 'f'")

    @Query("SELECT p FROM PostEntity p WHERE p.channel.id = :id AND NOT(p.archived)")
    List<PostEntity> findAllPosts(@Param("id") Long id);

    @Query("SELECT u FROM PostEntity u WHERE u.modified_at = (SELECT MAX(u.modified_at) FROM PostEntity u) AND NOT(u.archived)")
    PostEntity findLatestPost();

    @Query("SELECT u FROM PostEntity u INNER JOIN ChannelEntity a ON a.id = :id WHERE u.modified_at = (SELECT MAX(u.modified_at) FROM PostEntity u) AND NOT(u.archived)")
    PostEntity findLatestPostOfChannel(@Param("id") Long id);
}