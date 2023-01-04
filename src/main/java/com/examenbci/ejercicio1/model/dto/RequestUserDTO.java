package com.examenbci.ejercicio1.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class RequestUserDTO {
	
	private String name;
	private String email;
	private String password;
	private List<PhoneLineDTO> phones;
	
}
