package com.examenbci.ejercicio1.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.examenbci.ejercicio1.controller.UserController;
import com.examenbci.ejercicio1.exceptions.EmailAlreadyExistException;
import com.examenbci.ejercicio1.exceptions.InvalidExpresionEmail;
import com.examenbci.ejercicio1.exceptions.InvalidExpresionPassword;
import com.examenbci.ejercicio1.exceptions.UserException;
import com.examenbci.ejercicio1.exceptions.UserNotExistsException;
import com.examenbci.ejercicio1.model.User;
import com.examenbci.ejercicio1.model.dto.PhoneLineDTO;
import com.examenbci.ejercicio1.model.dto.RequestUserDTO;
import com.examenbci.ejercicio1.repository.UserRepository;

public class UserControllerTest {

	@Mock	
	UserService userService;
	@InjectMocks
	UserController userController;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
		
	@Test
    public void getAllUsersOkTest(){
        List<User> list = new ArrayList<>();
        list.add(createUserOk());
        when(this.userService.getAll()).thenReturn(list);
        ResponseEntity<List<User>> response = userController.getAllUsers();
        Assertions.assertEquals(list.size(),response.getBody().size());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
	
	@Test
    public void getUserByIdOkTest() throws UserNotExistsException{
		long idUser = 1;
		User user = createUserOk();
        when(this.userService.getById(idUser)).thenReturn(user);
        ResponseEntity<User> response = userController.getUserById(idUser);
        Assertions.assertEquals(user,response.getBody());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
	
	
	@Test
    public void getUserByIdUserNotExistsExceptionTest() throws UserNotExistsException{
		Assertions.assertThrows(UserNotExistsException.class, () -> {
			long idUser = 1;
	        when(this.userService.getById(idUser)).thenThrow(new UserNotExistsException());
	        ResponseEntity<User> response = userController.getUserById(idUser);
	        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        });
    }
		
	@Test
    public void deleteUserTest() throws UserNotExistsException{
		long idUser = 1;
		User user = createUserOk();
    	when(this.userService.getById(idUser)).thenReturn(user);  
        Boolean present = this.userService.getById(idUser)!=null?true:false;
        this.userService.delete(user);
        when(this.userService.getById(idUser)).thenReturn(null);  
        Boolean present2 = this.userService.getById(idUser)!=null?true:false;;        
        Assertions.assertTrue(present);
        Assertions.assertFalse(present2);
    }
	
	@Test
    public void deleteUserNotExistsExceptionTest() throws UserNotExistsException{
		Assertions.assertThrows(UserNotExistsException.class, () -> {
			long idUser = 1;
	        when(this.userService.getById(idUser)).thenThrow(new UserNotExistsException());
	        ResponseEntity<User> response = userController.getUserById(idUser);
	        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        });
    }
	
	@Test
    public void updateUserTest() throws UserNotExistsException, EmailAlreadyExistException{
		long idUser = 1;
		User user = createUserOk();
		RequestUserDTO _userDTO = new RequestUserDTO();
		_userDTO.setName("Christian");
		_userDTO.setPassword("Contra123");
		_userDTO.setEmail("chris@bci.cl");
		_userDTO.setPhones(new ArrayList<PhoneLineDTO>());
    	when(this.userService.getById(idUser)).thenReturn(user);  	
    	this.userService.save(user);
    	ResponseEntity<?> response = userController.updateUser(1,_userDTO);
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }
	
	@Test
    public void updateUserUserNotExistsExceptionTest() throws UserNotExistsException, EmailAlreadyExistException{
		        
        Assertions.assertThrows(UserNotExistsException.class, () -> {
			long idUser = 1;
	        when(this.userService.getById(idUser)).thenThrow(new UserNotExistsException());
	        ResponseEntity<User> response = userController.getUserById(idUser);
	        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        });
    }
	
	
	@Test
    public void saveUserTest() throws UserNotExistsException, EmailAlreadyExistException, InvalidExpresionEmail{
		long idUser = 1;
		User user = createUserOk();
		RequestUserDTO _userDTO = new RequestUserDTO();
		_userDTO.setName("Christian");
		_userDTO.setPassword("Contra123");
		_userDTO.setEmail("chris@bci.cl");
		_userDTO.setPhones(new ArrayList<PhoneLineDTO>());
    	when(this.userService.getById(idUser)).thenReturn(user);  	
    	this.userService.save(user);
    	ResponseEntity<?> response = userController.createUser(_userDTO);
        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }
	
	@Test
    public void saveUserUserNotExistsExceptionTest() throws UserNotExistsException, EmailAlreadyExistException{
		        
        Assertions.assertThrows(UserNotExistsException.class, () -> {
			long idUser = 1;
	        when(this.userService.getById(idUser)).thenThrow(new UserNotExistsException());
	        ResponseEntity<User> response = userController.getUserById(idUser);
	        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        });
    }
	
	
	private User createUserOk(){
        User newUser = new User();
        try {
        	newUser.setId(1);
	        newUser.setName("Christian");
			newUser.setPassword("Contra123");
			newUser.setEmail("chris@bci.cl");		
	        newUser.setIsActive(true);
	        newUser.setPhones(null);
        } catch (InvalidExpresionPassword | InvalidExpresionEmail e) {
			e.printStackTrace();
		}
        return newUser;
    }
}
