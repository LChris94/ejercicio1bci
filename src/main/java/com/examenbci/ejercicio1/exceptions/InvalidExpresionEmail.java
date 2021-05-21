package com.examenbci.ejercicio1.exceptions;

public class InvalidExpresionEmail extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidExpresionEmail() {
		super("Expresion de email invalida. (aaaaaaa@dominio.cl)");
	}
}
