package com.basic.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userName;
    private String email;
    private String phoneNumber;
    private String address;
    private String pinCode;
    private String city;
    private String country;
    
    
	
    public User(Long id, String userName, String email, String phoneNumber, String address, String pinCode, String city,
			String country) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.pinCode = pinCode;
		this.city = city;
		this.country = country;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long userId, String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
    
    
    
    
	
}
