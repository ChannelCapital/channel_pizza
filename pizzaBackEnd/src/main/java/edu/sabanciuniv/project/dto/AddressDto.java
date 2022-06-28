package edu.sabanciuniv.project.dto;

public class AddressDto {

	private String id;
	private String userId;
	private String country;
	private String city;
	private String postcode;
	
	public AddressDto() {
		super();
	}
	
	public AddressDto(String userId, String country, String city, String postcode) {
		super();
		this.userId = userId;
		this.country = country;
		this.city = city;
		this.postcode = postcode;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
}
