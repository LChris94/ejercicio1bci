package com.examenbci.ejercicio1.exceptions;

import com.examenbci.ejercicio1.controller.UserController;
import com.examenbci.ejercicio1.model.dto.MessageError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {

    Logger logger = LogManager.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(UserNotExistsException.class)
    @ResponseBody
    public ResponseEntity<Object> userErrorException(UserNotExistsException e){
        MessageError response = new MessageError(e.getMessage());
        logger.debug(e.getMessage());
        return new ResponseEntity<>(response,null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseBody
    public ResponseEntity<Object> emailAlreadyExistException(EmailAlreadyExistException e){
        MessageError response = new MessageError(e.getMessage());
        logger.debug(e.getMessage());
        return new ResponseEntity<>(response,null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidExpresionEmail.class)
    @ResponseBody
    public ResponseEntity<Object> invalidExpresionEmail(InvalidExpresionEmail e){
        MessageError response = new MessageError(e.getMessage());
        logger.debug(e.getMessage());
        return new ResponseEntity<>(response,null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidExpresionPassword.class)
    @ResponseBody
    public ResponseEntity<Object> invalidExpresionPassword(InvalidExpresionPassword e){
        MessageError response = new MessageError(e.getMessage());
        logger.debug(e.getMessage());
        return new ResponseEntity<>(response,null, HttpStatus.BAD_REQUEST);
    }
}
