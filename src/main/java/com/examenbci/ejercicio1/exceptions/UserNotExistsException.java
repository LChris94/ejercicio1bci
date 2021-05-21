package com.examenbci.ejercicio1.exceptions;

public class UserNotExistsException extends Throwable{

	private static final long serialVersionUID = 1L;
	
	public UserNotExistsException() {
		super("El Usuario no existe.");
	}
}
