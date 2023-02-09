package com.projet.forum.Repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projet.forum.Entities.*;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    @Query(value= "SELECT u.created_at, u.modified_at FROM UserEntity u WHERE u.id = :id")
    List<LocalDate> showAccountCreationDate(@Param(value = "id") Long id);

    Optional<UserEntity> findByMail(String mail);
}
