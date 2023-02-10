package com.projet.forum.Controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

import com.projet.forum.Dtos.UserDtos.UserConnectionDto;
import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Repositories.UserRepository;
import com.projet.forum.Services.UserServices.UserServiceImpl;


import jakarta.websocket.server.PathParam;

@RestController()
@RequestMapping("/sign_up")
public class UserController {
    
    private final UserServiceImpl service;

    public UserController(UserRepository repo, UserServiceImpl ser){

        this.service = ser;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserConnectionDto> getCredentials
    (@RequestParam(name="mail") String mail, @RequestParam(name="password") String password, @RequestParam(name="login") String login){

        UserConnectionDto user_credentials = new UserConnectionDto(mail, password, login);
        return ResponseEntity.ok(user_credentials);
    }

    @RequestMapping(path = "/create_account", method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserEntity> createAccount(@Valid @RequestBody UserConnectionDto newUser){ //add authentification

        UserEntity user = service.createUser(newUser.mail(),newUser.password(),newUser.login());
        return ResponseEntity.ok(user);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public String deleteAccount(@RequestParam(name = "id") Long id){

        service.archiviseUser(id);
        return "deleted";
    }
}
