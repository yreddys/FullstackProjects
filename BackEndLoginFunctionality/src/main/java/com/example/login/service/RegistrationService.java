package com.example.login.service;

import com.example.login.entity.Registration;
import com.example.login.exception.AuthenticationFailedException;
import com.example.login.exception.EmailAlreadyRegisteredException;

public interface RegistrationService {

	Registration saveRegistration(Registration registration) throws EmailAlreadyRegisteredException;

	Registration authenticateUser(String email, String password) throws AuthenticationFailedException;

}
