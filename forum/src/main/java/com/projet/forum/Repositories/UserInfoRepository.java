package com.projet.forum.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.projet.forum.Entities.UserInfoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity,Long>{

    @Query(value = "SELECT warnings FROM user_info_entity WHERE login = :login_user", nativeQuery = true)
    List<String> displayAllWarnings(@Param("login_user") String login_user);
}
