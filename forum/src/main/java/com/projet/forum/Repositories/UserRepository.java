package com.projet.forum.Repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>{
    
    @Query("SELECT created_at FROM UserEntity WHERE user_id = :id")
    LocalDate showAccountCreationDate(@Param(value = "id") Long id);

    Optional<UserEntity> findByMail(String mail);

}
