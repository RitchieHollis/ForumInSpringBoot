package com.projet.forum.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long>{
    
}