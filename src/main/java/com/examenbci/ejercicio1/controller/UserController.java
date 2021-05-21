package com.examenbci.ejercicio1.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenbci.ejercicio1.exceptions.EmailAlreadyExistException;
import com.examenbci.ejercicio1.exceptions.InvalidExpresionEmail;
import com.examenbci.ejercicio1.exceptions.InvalidExpresionPassword;
import com.examenbci.ejercicio1.exceptions.UserException;
import com.examenbci.ejercicio1.exceptions.UserNotExistsException;
import com.examenbci.ejercicio1.model.PhoneLine;
import com.examenbci.ejercicio1.model.User;
import com.examenbci.ejercicio1.model.dto.MessageError;
import com.examenbci.ejercicio1.model.dto.RequestUserDTO;
import com.examenbci.ejercicio1.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/user")
public class UserController {
	
	Logger logger = LogManager.getLogger(UserController.class);

	private final UserService userService;
	
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAll());
    }
    
    @GetMapping("{idUser}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "idUser", required = true)long idUser) throws UserNotExistsException {
        return ResponseEntity.ok(this.userService.getById(idUser));
    }
    
    @DeleteMapping("{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "idUser", required = true) long idUser) throws UserNotExistsException {
        User _user = userService.getById(idUser);
    	this.userService.delete(_user);
        return ResponseEntity.ok().build();
    }
    
    
    @PutMapping("{idUser}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "idUser", required = true) long idUser, @RequestBody RequestUserDTO requestUserDTO) throws UserNotExistsException {
		ResponseEntity<?> response;
		try {
	    User _user = userService.getById(idUser);    	
		_user.setName(requestUserDTO.getName());
    	_user.setEmail(requestUserDTO.getEmail());
    	_user.setPassword(requestUserDTO.getPassword());
    	if(Objects.isNull(requestUserDTO.getPhones())) {
    		List<PhoneLine> _list = new ArrayList<PhoneLine>();
    		requestUserDTO.getPhones().forEach(e->_list.add(new PhoneLine(e.getId(), e.getNumber(), e.getCitycode(), e.getContrycode())));
    		_user.setPhones(_list);
    	}    	
    	_user.setModified(LocalDateTime.now());    	
		userService.save(_user);
		response = ResponseEntity.ok(_user); 
		} catch (EmailAlreadyExistException | InvalidExpresionEmail | InvalidExpresionPassword e) {
			logger.error(e.getMessage());
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    	return response;  		        
    }
    
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody RequestUserDTO requestUserDTO) throws InvalidExpresionEmail{
    	ResponseEntity<?> response;
    	try {
	    	User _user = new User();
	    	_user.setName(requestUserDTO.getName());
	    	_user.setEmail(requestUserDTO.getEmail());
	    	_user.setPassword(requestUserDTO.getPassword());
	    	if(Objects.isNull(requestUserDTO.getPhones())) {
	    		List<PhoneLine> _list = new ArrayList<PhoneLine>();
	    		requestUserDTO.getPhones().forEach(e->_list.add(new PhoneLine(e.getId(), e.getNumber(), e.getCitycode(), e.getContrycode())));
	    		_user.setPhones(_list);
	    	}  
	        _user.setIsActive(true);
	        _user.setToken(getJWTToken(requestUserDTO.getName())); 
			userService.save(_user);
			response = ResponseEntity.status(HttpStatus.CREATED).body(_user);
		} catch (EmailAlreadyExistException | InvalidExpresionEmail | InvalidExpresionPassword e) {
			logger.error(e.getMessage());
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageError(e.getMessage()));
		}
		return response;
    }
    
    private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
    
    
    
    
}
