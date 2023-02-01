package com.projet.forum.Repositories;

import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity,Long>{
    
}