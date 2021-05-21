package com.examenbci.ejercicio1.service;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.util.Optionals;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.examenbci.ejercicio1.exceptions.EmailAlreadyExistException;
import com.examenbci.ejercicio1.exceptions.InvalidExpresionEmail;
import com.examenbci.ejercicio1.exceptions.InvalidExpresionPassword;
import com.examenbci.ejercicio1.exceptions.UserNotExistsException;
import com.examenbci.ejercicio1.model.User;
import com.examenbci.ejercicio1.repository.UserRepository;

public class UserServiceTest {
	
	@Mock
    private UserRepository userRepository;

    private UserService userService;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.userService = new UserService(userRepository);
    }
    
    @Test
    public void getUserByIdOkTest() throws UserNotExistsException {
        long id = 1;
        User user = createUserOk();
        when(this.userRepository.findById(id)).thenReturn(user);
        User test = this.userService.getById(id);
        Assertions.assertEquals(user, test);

    }
    
    @Test
    public void getUserByIdUserNotExistsExceptionTest() throws UserNotExistsException {
      Assertions.assertThrows(UserNotExistsException.class, () -> {
        	long id = 1;
        	User user = createUserOk();
        	when(this.userRepository.findById(id)).thenReturn(user);
            User test = this.userService.getById(2);
            Assertions.assertEquals(user, test);
        });
    }
    
    @Test
    public void getAllTest() {
        User user1 = createUserOk();
        List<User> users = Arrays.asList(user1);
        when(this.userRepository.findAll()).thenReturn(users);
        List<User> test = this.userService.getAll();
        Assertions.assertEquals(users, test);

    }
    
    @Test
    public void getAllActiveTest() {
        User user1 = createUserOk();
        List<User> users = Arrays.asList(user1);
        when(this.userRepository.findAll()).thenReturn(users);
        List<User> test = this.userService.getAllActives();
        Assertions.assertEquals(users.get(0).getIsActive(), test.get(0).getIsActive());

    }    
    
    @Test
    public void getUserByEmailTest() {
    	String email = "aaaaa@gmail.cl";
        User user = createUserOk();
        when(this.userRepository.findByEmail(email)).thenReturn(user);
        User test = this.userService.getByEmail(email);
        Assertions.assertEquals(user, test);
    }
    
    @Test
    public void saveUserOkTest() throws EmailAlreadyExistException {
    	String email = "chriss@bci.cl";
    	User userToCreate = createUserOk();
        when(this.userRepository.findByEmail(email)).thenReturn(null);
        when(this.userService.save(userToCreate)).thenReturn(userToCreate);
        Assertions.assertEquals(userToCreate.getId(),1);
    }
    
    @Test
    public void saveUserEmailAlreadyExistExceptionTest() throws EmailAlreadyExistException {
    	Assertions.assertThrows(EmailAlreadyExistException.class, () -> {
    		String email = "chris@bci.cl";
        	User userToCreate = createUserOk();
        	User _test = new User();
            _test.setId(0);
            _test.setEmail("chris@bci.cl");
            when(this.userRepository.findByEmail(email)).thenReturn(_test);            
            when(this.userService.save(userToCreate)).thenReturn(userToCreate);
        });
    }
    
    @Test
    public void deleteUserOkTest() throws EmailAlreadyExistException {
    	User user = createUserOk();
    	when(this.userRepository.findById(1)).thenReturn(user);  
        Boolean present = userRepository.findById(1)!=null?true:false;
        this.userService.delete(user);
        when(this.userRepository.findById(1)).thenReturn(null);  
        Boolean present2 = userRepository.findById(1)!=null?true:false;;        
        Assertions.assertTrue(present);
        Assertions.assertFalse(present2);
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
    
    private User createUserInvalidExpresionPassword() throws InvalidExpresionPassword, InvalidExpresionEmail{
        User newUser = new User();
        newUser.setId(1);
        newUser.setName("Christian");
        newUser.setPassword("contra123");
        newUser.setEmail("chris@bci.cl");
        newUser.setIsActive(true);
        newUser.setPhones(null);
        return newUser;
    }
    
    private User createUserInvalidExpresionEmail() throws InvalidExpresionPassword, InvalidExpresionEmail{
        User newUser = new User();
        newUser.setId(1);
        newUser.setName("Christian");
        newUser.setPassword("Contra123");
        newUser.setEmail("chris@bci");
        newUser.setIsActive(true);
        newUser.setPhones(null);
        return newUser;
    }
    
}
