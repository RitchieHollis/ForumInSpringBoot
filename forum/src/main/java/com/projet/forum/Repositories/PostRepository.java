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
    public List<MessageEntity> findAllMessages(Long id);

    @Query("SELECT u.created_at, u.modified_at FROM PostEntity u WHERE u.id = :id")
    List<LocalDate> displayTimeInfo(@Param("id") Long id);
}