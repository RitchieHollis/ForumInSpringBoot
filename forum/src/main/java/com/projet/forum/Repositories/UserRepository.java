package com.projet.forum.Repositories;

import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>{
    
}
