package com.examenbci.ejercicio1.model.dto;

import java.util.List;



public class RequestUserDTO {
	
	private String name;
	private String email;
	private String password;
	private List<PhoneLineDTO> phones;
	
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<PhoneLineDTO> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneLineDTO> phones) {
		this.phones = phones;
	}
	
	
}
