package com.projet.forum.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class LoginController {
    
    @GetMapping("/login")
	@ResponseBody
	String login() {
		return "login";
	}
}
