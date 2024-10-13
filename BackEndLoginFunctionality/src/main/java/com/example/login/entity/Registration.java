package com.example.login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Registration {
	@Id
	@GeneratedValue
	private int registrationId;
	private String name;
	private String password;
	private String email;
	public int getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Registration(int registrationId, String name, String password, String email) {
		
		this.registrationId = registrationId;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	public Registration() {}
	@Override
	public String toString() {
		return "Registration [registrationId=" + registrationId + ", name=" + name + ", password=" + password
				+ ", email=" + email + "]";
	}
	
	
}
