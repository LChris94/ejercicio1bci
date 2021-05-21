package com.examenbci.ejercicio1.exceptions;

public class EmailAlreadyExistException extends Throwable {
	

	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyExistException() {
		super("El correo ya registrado");
	}

}
