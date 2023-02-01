package com.projet.forum.Repositories;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.MessageEntity;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity,Long>{
    
    @Query("SELECT created_at, modified_at FROM MessageEntity WHERE message_id = :id")
    Map<LocalDate, LocalDate> displayTimeInfo(@Param("id") Long id);

    @Query("SELECT login FROM UserInfoEntity WHERE (user_id = MessageEntity.user_id) AND (MessageEntity.message_id = :id)")
    String displayUser(@Param("id") Long id);
}
