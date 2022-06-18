package com.project.commercial.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

/**
 * This class is generated for determining the user features and create user objects
 * as users. When user sign up and login  these constructors are called and created.
 * @author fatih akguc
 * @param email the email address of the user
 * @param password the password of the user
 * @param phone the phone number of the user
 * @param address the address name of the place user lives in
 * @param address2 the street and apartment number of the user
 * @param region the region of the address
 * @param city the city of the user
 * @param zip the post code of the user address
 */

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Document(collection = "User")
public class User {
	
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	
	@Id
	private int id;
	private String email;
	private String password;
	private String phone;
	private String address;
	private String address2;
	private String region;
	private String country;
	private String zip;
	

	public User() {
		super();
	}

	

	public User(int id, String email, String password, String phone, String address, String address2, String region, String country, String zip) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.address2 = address2;
		this.region = region;
		this.country = country;
		this.zip = zip;
	}

	public User(String email, String password, String phone, String address, String address2, String region, String country, String zip) {
		super();
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.address2 = address2;
		this.region = region;
		this.country = country;
		this.zip = zip;
	}
	

	public User(int id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}



	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}


/*
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", phone=" + phone + ", address="
				+ address + ", address2=" + address2 + ", region=" + region + ", country=" + country + ", zip=" + zip
				+ "]";
	}

*/
}
