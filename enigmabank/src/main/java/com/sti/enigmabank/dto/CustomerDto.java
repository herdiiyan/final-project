package com.sti.enigmabank.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerDto {

	private String cif;
	private String firstName;
	private String lastName;
	private String address;
	@JsonFormat(pattern ="dd-MM-yyyy")
	private Date birthDate;
	private String birthPlace;
	
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
}
