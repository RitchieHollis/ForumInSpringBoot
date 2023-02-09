package com.projet.forum.Services.UserServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.projet.forum.Entities.Role;
import com.projet.forum.Entities.Status;
import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Entities.UserInfoEntity;
import com.projet.forum.Exceptions.UserExceptions.UserAlreadyExistException;
import com.projet.forum.Exceptions.UserExceptions.InexistantUserException;
import com.projet.forum.Repositories.UserRepository;
import com.projet.forum.Repositories.UserInfoRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired

    private UserRepository repository;
    private UserInfoRepository i_repository;

    public UserServiceImpl(UserRepository ur, UserInfoRepository uir){
        this.repository = ur;
        this.i_repository = uir;
    }

    @Override public UserEntity createUser(String mail, String password, String login){
        
        UserEntity newUser = new UserEntity();
        newUser.setMail(mail);
        newUser.setPassword(password);
        newUser.setRole(Role.USER);

        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setLogin(login);
        userInfo.setStatus(Status.OFFLINE); //for testing, auto value to implement in service/controller
        newUser.setUser_info(userInfo);
        
        if(repository.exists(Example.of(newUser)))
            throw new UserAlreadyExistException("This mail is already used");
        else{

            i_repository.save(userInfo);
            UserEntity savedUser = repository.save(newUser);
            return savedUser;
        }
    }
    @Override public void connect(String mail, String password, String login){
        
        
    }
    @Override public void deleteUser(Long id){

        if(repository.findById(id) == null)
            throw new InexistantUserException("This user doesn't exist");
        else{
            i_repository.deleteById(repository.findById(id).get().getUser_info().getId());
            repository.deleteById(id);
        }
    }
    @Override public UserEntity saveUser(UserEntity user){

        return repository.save(user);
    }
    @Override public List<UserEntity> findAllUsers(){

        return repository.findAll();
    }
}
