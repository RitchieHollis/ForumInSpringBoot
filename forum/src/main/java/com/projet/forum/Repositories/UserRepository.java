package com.projet.forum.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{
    
}
