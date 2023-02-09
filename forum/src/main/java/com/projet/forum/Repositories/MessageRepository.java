package com.projet.forum.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.MessageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface MessageRepository extends JpaRepository<MessageEntity,Long>{
    
    @Query("SELECT u.created_at FROM MessageEntity u WHERE u.id = :id")
    LocalDate displayCreatedAt(@Param(value = "id") Long id);

    @Query("SELECT u.modified_at FROM MessageEntity u WHERE u.id = :id")
    LocalDate displayModifiedAt(@Param(value = "id") Long id);

    @Query("SELECT u FROM MessageEntity u WHERE u.user_author = :id")
    List<MessageEntity> findAllMessagesOfUser(@Param("id") Long id);
}
