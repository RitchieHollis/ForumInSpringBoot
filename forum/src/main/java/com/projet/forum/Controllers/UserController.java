package com.projet.forum.Controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

import com.projet.forum.Dtos.UserDtos.UserConnectionDto;
import com.projet.forum.Entities.Role;
import com.projet.forum.Entities.UserEntity;
//import com.projet.forum.Repositories.UserRepository;
import com.projet.forum.Services.UserServices.UserServiceImpl;

@RestController
@RequestMapping("/sign_up")
public class UserController {
    
    private final UserServiceImpl service;

    public UserController(/*UserRepository repo,*/ UserServiceImpl ser){

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

    @RequestMapping(path = "/admin/delete", method = RequestMethod.GET)
    @ResponseBody
    public String deleteAccount(@RequestParam(name = "id") Long id){

        service.archiviseUser(id);
        return "User deleted";
    }

    @PostMapping("/admin/giveAdmin")
    public void setAdmin(@RequestParam(name = "id") Long id, @RequestParam(name = "id_set") Long id2){
        
        service.giveAdminPermission(id, id2);
    }

   /* @PostMapping("/giveAdminTest")
    public void setAdminTest(@RequestParam(name = "id") Long id){
        
        service.giveAdminPermission(id);
    }*/
}
