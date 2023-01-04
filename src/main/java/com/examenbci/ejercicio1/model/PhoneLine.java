package com.examenbci.ejercicio1.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class PhoneLine {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String number;
	
	private String citycode;
	
	private String contrycode;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;
	
	public PhoneLine() {
	}
		
	public PhoneLine(Long id, String number, String citycode, String contrycode) {
		super();
		setId(id);
		setNumber(number);
		setCitycode(citycode);
		setContrycode(contrycode);
	}

}
