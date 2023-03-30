package com.projet.forum.Controllers;

import java.security.Principal;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet.forum.Dtos.UserDtos.UserConnectionDto;
import com.projet.forum.Entities.UserEntity;
import com.projet.forum.Services.UserServices.UserService;
import com.projet.forum.Utils.JwtHelper;

@RestController
@RequestMapping()
public class LoginController {
    
	private final UserService userService;
    private final JwtHelper jwtHelper;
    private final AuthenticationManager authenticationManager;

	@Autowired
    public LoginController(UserService userService, JwtHelper jwtHelper, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
        this.authenticationManager = authenticationManager;
    }

	@PostMapping(path = "/login")
	@ResponseBody
    public ResponseEntity<?> signInAction(@RequestBody UserConnectionDto form) {
		
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.login(), form.password()));

        final UserEntity userDetails = userService.findUserByUsername(form.login()).get();
        final String token = jwtHelper.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }

	@GetMapping("/testAuth")
	public ResponseEntity<?> testAuth(Principal principal) {
		System.out.println(principal.getName());
		return ResponseEntity.ok(" Authentifié !" + principal.getName());
	}

/* 
    @GetMapping("/login")
	@ResponseBody
	String login() {
		return "login";
	}*/
}
//localhost:8080/posts/createPost?channelId=1?title=Presentations?userId=2?content=Bonjour, presentez-vous