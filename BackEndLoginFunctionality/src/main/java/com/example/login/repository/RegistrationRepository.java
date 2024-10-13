package com.example.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.entity.Login;
import com.example.login.entity.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	boolean existsByEmail(String email);

	Registration findByEmail(String email);

	
	boolean existsByEmailAndPassword(String email, String password);

}
