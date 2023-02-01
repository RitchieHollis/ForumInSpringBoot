package com.projet.forum.Repositories;

import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.UserInfoEntity;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfoEntity,Long>{
    
}
