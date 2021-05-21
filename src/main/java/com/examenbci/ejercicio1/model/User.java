package com.examenbci.ejercicio1.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.examenbci.ejercicio1.exceptions.InvalidExpresionEmail;
import com.examenbci.ejercicio1.exceptions.InvalidExpresionPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@JsonIgnore
	private String name;
	
	@JsonIgnore
	private String email;
	
	@JsonIgnore
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<PhoneLine> phones;
	
	private LocalDateTime created;
	
	private LocalDateTime modified;
	
	private LocalDateTime last_login;
	
	private String token;
	
	private Boolean isActive;
	
	@JsonIgnore
	@Transient
	private final String emailRegexp = "[a-z]+@[a-z]+.cl";
	
	@JsonIgnore
	@Transient
	private final String passwordRegexp = "^[A-Z][a-z]+[0-9]{2,}";
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) throws InvalidExpresionEmail {
		if(email.matches(emailRegexp)) {
			this.email = email;
		} else {
			throw new InvalidExpresionEmail();
		}
		
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws InvalidExpresionPassword {
		if(password.matches(passwordRegexp)) {
			this.password = password;
		} else {
			throw new InvalidExpresionPassword();
		}		
	}
	public List<PhoneLine> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneLine> phones) {
		this.phones = phones;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getModified() {
		return modified;
	}
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	public LocalDateTime getLast_login() {
		return last_login;
	}
	public void setLast_login(LocalDateTime last_login) {
		this.last_login = last_login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	@PrePersist
	public void persistCreation() {
		if(Objects.isNull(created)) {
			created = LocalDateTime.now();
		}	
		if(Objects.isNull(last_login)) {
			last_login = LocalDateTime.now();
		}	
	}
	
	
}
