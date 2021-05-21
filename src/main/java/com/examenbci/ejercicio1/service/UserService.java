package com.examenbci.ejercicio1.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examenbci.ejercicio1.exceptions.EmailAlreadyExistException;
import com.examenbci.ejercicio1.exceptions.UserNotExistsException;
import com.examenbci.ejercicio1.model.User;
import com.examenbci.ejercicio1.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public List<User> getAll() {
        return userRepository.findAll();
    }
    
    public List<User> getAllActives() {
        return userRepository.findAll().stream().filter(e->e.getIsActive()).collect(Collectors.toList());
    }
	
	public User getById(long idUser) throws UserNotExistsException {
        User user = this.userRepository.findById(idUser);
        return Optional.ofNullable(user).orElseThrow(() -> new UserNotExistsException());
    }
	
	public User getByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
	
	public User save(User user) throws EmailAlreadyExistException {
		User _userTemp = getByEmail(user.getEmail());
		if(!Objects.isNull(_userTemp) && user.getId()!=_userTemp.getId()) {
			throw new EmailAlreadyExistException();
		}
		return userRepository.save(user);
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	


}
