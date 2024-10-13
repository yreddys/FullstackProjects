package com.example.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticationFailedExceptionHandling {
	@ExceptionHandler(AuthenticationFailedException.class)
	ResponseEntity<String> handlingAuthentication(AuthenticationFailedException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
	}
}
