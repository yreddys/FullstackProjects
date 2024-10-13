package com.example.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmailAlreadyRegisteredExceptionHandling {
	@ExceptionHandler(EmailAlreadyRegisteredException.class)
	ResponseEntity<String> handlingException(EmailAlreadyRegisteredException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CREATED);
	}
}
