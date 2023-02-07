package com.projet.forum.Services.UserServices;

import java.util.List;

import com.projet.forum.Entities.UserEntity;

public interface UserService {
    
    public abstract UserEntity createUser(String mail, String password, String login);

    public abstract void connect(String mail, String password, String login);

    public abstract void deleteUser(Long id);

    public abstract UserEntity saveUser(UserEntity user);

    public abstract List<UserEntity> findAllUsers();

}
