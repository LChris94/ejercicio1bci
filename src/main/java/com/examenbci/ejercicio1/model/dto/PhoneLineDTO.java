package com.examenbci.ejercicio1.model.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PhoneLineDTO {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String number;
	
	private String citycode;
	
	private String contrycode;

	
	public PhoneLineDTO() {
	}
		
	public PhoneLineDTO(Long id, String number, String citycode, String contrycode) {
		super();
		setId(id);
		setNumber(number);
		setCitycode(citycode);
		setContrycode(contrycode);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getContrycode() {
		return contrycode;
	}
	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}	
}
