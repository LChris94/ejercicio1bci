package com.examenbci.ejercicio1.model.dto;

import lombok.Data;


@Data
public class PhoneLineDTO {

	private Long id;
	private String number;
	private String citycode;
	private String contrycode;

}
