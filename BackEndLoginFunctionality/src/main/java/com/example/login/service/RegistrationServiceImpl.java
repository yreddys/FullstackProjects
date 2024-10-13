package com.example.login.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.login.controller.RegistrationController;
import com.example.login.entity.Login;
import com.example.login.entity.Registration;
import com.example.login.exception.AuthenticationFailedException;
import com.example.login.exception.EmailAlreadyRegisteredException;

import com.example.login.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

   

    @Autowired
    private RegistrationRepository registrationRepository;

    private Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @Override
    public Registration saveRegistration(Registration registration) throws EmailAlreadyRegisteredException {
        log.info("Registration details {}", registration);

        // Check if the email is already registered
        if (emailAlreadyRegistered(registration.getEmail())) {
            log.warn("Email {} is already registered.", registration.getEmail());
            throw new EmailAlreadyRegisteredException("Email is already registered.");
        }

        Registration savedRegistration = registrationRepository.save(registration);
        log.info("Registration details saved successfully {}", savedRegistration);
        return savedRegistration;
    }

    private boolean emailAlreadyRegistered(String email) {
        return registrationRepository.existsByEmail(email);
    }

    @Override
    public Registration authenticateUser(String email, String password) throws AuthenticationFailedException {
        log.info("Authentication request for user with email: {}", email);

        // Implement logic to authenticate the user based on the provided email and password
        if (registrationRepository.existsByEmailAndPassword(email, password)) {
            Registration authenticatedUser = registrationRepository.findByEmail(email);
            log.info("User with email {} authenticated successfully.", email);
            return authenticatedUser;
        } else {
            // Handle authentication failure, you can throw an exception or return null
            log.warn("Authentication failed for user with email: {}", email);
            throw new AuthenticationFailedException("Authentication failed");
        }
    }
}
