package com.examenbci.ejercicio1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenbci.ejercicio1.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	
    @Autowired
    public ApiController(UserService userService) {
        
    }
    

}
