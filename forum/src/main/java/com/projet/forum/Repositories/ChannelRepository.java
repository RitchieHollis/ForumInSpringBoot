package com.projet.forum.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.ChannelEntity;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity,Long>{
    
}