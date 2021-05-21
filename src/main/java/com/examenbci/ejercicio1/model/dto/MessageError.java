package com.examenbci.ejercicio1.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageError {
	@JsonProperty("mensaje")
	private String message;

	
	
	public MessageError(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
