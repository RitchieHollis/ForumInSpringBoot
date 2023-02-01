package com.projet.forum.Services.UserServices;

import com.projet.forum.Entities.UserEntity;

public interface UserService {
    
    public abstract UserEntity createUser(String mail, String password, String login);

    public abstract void connect(String mail, String password, String login);

    public abstract void deleteUser(Long id);

}
