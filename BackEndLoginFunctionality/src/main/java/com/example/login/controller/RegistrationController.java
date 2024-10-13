package com.example.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.entity.Login;
import com.example.login.entity.Registration;
import com.example.login.exception.AuthenticationFailedException;
import com.example.login.exception.EmailAlreadyRegisteredException;
import com.example.login.service.RegistrationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;
	private Logger log = LoggerFactory.getLogger(RegistrationController.class);

	@PostMapping("/save")
	String saveRegistration(@RequestBody Registration registration) throws EmailAlreadyRegisteredException {
		log.info("Request details for registration{}", registration);
		registrationService.saveRegistration(registration);
		return "You are registered successfully";
	}
	
	 @PostMapping("/login")
	    public ResponseEntity<String> loginUser(@RequestBody Login loginRequest) throws AuthenticationFailedException {
	        Registration authenticatedUser = registrationService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
			return ResponseEntity.ok("Login successful. Welcome, " + authenticatedUser.getName() + "!");
	    }
}
