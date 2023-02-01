package com.projet.forum.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.projet.forum.Entities.UserInfoEntity;
import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.Badge;
import java.util.List;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfoEntity,Long>{
    
    @Query("SELECT * FROM MessageEntity WHERE user_id = :id")
    List<MessageEntity> findAllMessagesOfUser(@Param("id") Long id);

    @Query("SELECT badges FROM UserEntity WHERE user_id = :id")
    List<Badge> displayAllbadges(@Param("id") Long id);

    @Query("SELECT warnings FROM UserEntity WHERE user_id = :id")
    List<String> displayAllWarnings(@Param("id") Long id);
}
