package com.projet.forum.Services.UserServices;

import java.util.List;
import java.util.Optional;

import com.projet.forum.Dtos.UserInfoDtos.UserInfoStateDto;
import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.UserEntity;

public interface UserService {
    
    public abstract UserEntity createUser(String mail, String password, String login);

    public abstract void connect(String mail, String password, String login);
    
    public abstract void archiviseUser(Long id);

    public abstract UserEntity saveUser(UserEntity user);

    public abstract List<UserEntity> findAllUsers();

    public abstract Optional<UserEntity> findUserById(Long id);

    public abstract Optional<UserEntity> findUserByUsername(String username);

    public abstract int findTotalMessagesOfUser(Long id);

    public abstract List<MessageEntity> findAllMessagesOfUser(Long id);

    public abstract void giveAdminPermission(Long id, Long id2);

    public abstract void giveAdminPermission(Long id); //test
}
