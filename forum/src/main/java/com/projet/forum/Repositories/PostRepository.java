package com.projet.forum.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity,Long>{
    
    @Query("SELECT messages FROM PostEntity")
    public List<MessageEntity> findAllMessages(Long id);

    @Query("SELECT created_at, modified_at FROM postEntity WHERE post_id = :id")
    Map<LocalDate, LocalDate> displayTimeInfo(@Param("id") Long id);
}