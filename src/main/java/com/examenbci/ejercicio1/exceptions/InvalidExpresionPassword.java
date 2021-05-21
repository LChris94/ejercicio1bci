package com.examenbci.ejercicio1.exceptions;

public class InvalidExpresionPassword extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidExpresionPassword() {
		super("Expresion de password invalida. (Una May�scula, letras min�sculas, y dos n�meros)");
	}

}
