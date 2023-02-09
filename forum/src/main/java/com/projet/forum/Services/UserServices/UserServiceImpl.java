package com.projet.forum.Services.UserServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.projet.forum.Entities.Role;
import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Exceptions.UserExceptions.UserAlreadyExistException;
import com.projet.forum.Exceptions.UserExceptions.InexistantUserException;
import com.projet.forum.Repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired

    private UserRepository repository;

    public UserServiceImpl(UserRepository ur){
        this.repository = ur;
    }

    @Override public UserEntity createUser(String mail, String password, String login){
        
        UserEntity newUser = new UserEntity();
        newUser.setMail(mail);
        newUser.setPassword(password);
        newUser.setRole(Role.USER);
        newUser.getUser_info().setLogin(login);
        
        if(repository.exists(Example.of(newUser)))
            throw new UserAlreadyExistException("This mail is already used");
        else{

            UserEntity savedUser = repository.save(newUser);
            return savedUser;
        }
    }
    @Override public void connect(String mail, String password, String login){
        
        
    }
    @Override public void deleteUser(Long id){

        if(repository.findById(id) == null)
            throw new InexistantUserException("This user doesn't exist");
        else
            repository.deleteById(id);
    }
    @Override public UserEntity saveUser(UserEntity user){

        return repository.save(user);
    }
    @Override public List<UserEntity> findAllUsers(){

        return repository.findAll();
    }
}
