package com.projet.forum.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity,Long>{
    
}
