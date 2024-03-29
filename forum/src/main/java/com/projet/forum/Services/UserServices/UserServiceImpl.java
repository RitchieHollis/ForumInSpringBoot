package com.projet.forum.Services.UserServices;

import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.forum.Dtos.UserInfoDtos.UserInfoStateDto;
import com.projet.forum.Entities.MessageEntity;
import com.projet.forum.Entities.Role;
import com.projet.forum.Entities.Status;
import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Entities.UserInfoEntity;
import com.projet.forum.Repositories.UserRepository;
import com.projet.forum.Repositories.UserInfoRepository;
import com.projet.forum.Exceptions.UserNotAllowedException;
import com.projet.forum.Exceptions.UserExceptions.*;
import com.projet.forum.Repositories.MessageRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired

    private final UserRepository repository;
    private final UserInfoRepository i_repository;
    private final MessageRepository m_repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository ur, UserInfoRepository uir, MessageRepository mr, PasswordEncoder passwordEncoder){
        this.repository = ur;
        this.i_repository = uir;
        this.m_repository = mr;
        this.passwordEncoder = passwordEncoder;
    }

    @Override public UserEntity createUser(String mail, String password, String login){
        
        UserEntity newUser = new UserEntity();
        newUser.setMail(mail);
        password = passwordEncoder.encode(password);
        newUser.setPassword(password);
        newUser.setRole(Role.USER.USER);
        newUser.setCreated_at(LocalDateTime.now());
        newUser.setModified_at(newUser.getCreated_at());

        UserInfoEntity userInfo = new UserInfoEntity();
        userInfo.setLogin(login);
        userInfo.setCreated_at(LocalDateTime.now());
        userInfo.setModified_at(userInfo.getCreated_at());
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
    @Override public void archiviseUser(Long id){

        if(repository.findById(id) == null)
            throw new InexistantUserException("This user doesn't exist");
        else{
            UserEntity u = repository.findById(id).get();
            u.setArchived(true);
            u.setModified_at(LocalDateTime.now());
            repository.saveAndFlush(u);
        }
    }
    @Override public UserEntity saveUser(UserEntity user){

        UserInfoEntity user_info = i_repository.findById(user.getUser_info().getId()).orElseThrow();
        i_repository.save(user_info);
        return repository.save(user);
    }
    @Override public List<UserEntity> findAllUsers(){

        return repository.findAllLegalUsers();
    }
    @Override public Optional<UserEntity> findUserById(Long id){

        if(repository.findById(id).get().isArchived())
            throw new UserArchivisedException("User is unavailbe");

        return repository.findById(id);

    }
    @Override public Optional<UserEntity> findUserByUsername(String username){

        if(repository.findByUsername(username).get().isArchived())
            throw new UserArchivisedException("User is unavailbe");
            
        return repository.findByUsername(username);
    }
    @Override public int findTotalMessagesOfUser(Long id){

        UserEntity user = repository.findById(id).orElseThrow();

        if(user.isArchived()) throw new UserArchivisedException("User not found");

        List<MessageEntity> listMessages = m_repository.findAllMessagesOfUser(id);
        if(listMessages.isEmpty())
            return 0;
        else return listMessages.size();
    }
    @Override public List<MessageEntity> findAllMessagesOfUser(Long id){

        UserEntity user = repository.findById(id).orElseThrow();

        if(user.isArchived()) throw new UserArchivisedException("User not found");

        List<MessageEntity> listMessages = m_repository.findAllMessagesOfUser(id);
        return listMessages;
    }
    @Override public void giveAdminPermission(Long id, Long id_setUser){
        
        UserEntity user = repository.findById(id).orElseThrow();
        UserEntity user2 = repository.findById(id_setUser).orElseThrow();

        if(user.getRole().equals(Role.ADMIN)){

            user2.setRole(Role.ADMIN);
            repository.saveAndFlush(user2);
        }
            
        else throw new UserNotAllowedException("You don't have a specific role to execute this task");
    }
    @Override public void giveAdminPermission(Long id){

        UserEntity user = repository.findById(id).orElseThrow();

        user.setRole(Role.ADMIN);
        repository.saveAndFlush(user);
    }
    /* 
    private Set<GrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }*/
}
