package com.projet.forum.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path = "/{mail}/{password}/{login}", method = RequestMethod.GET)
    @ResponseBody
    public RequestMapping createAccount(@PathVariable("mail") String mail,
                                    @PathVariable("password") String password,
                                    @PathVariable("login") String login){
        UserEntity user = service.createUser(mail, password, login);
        return null;
    }
    @RequestMapping(path = "delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteAccount(@PathVariable("id") Long id){
        service.deleteUser(id);
        return "deleted";
    }
}
