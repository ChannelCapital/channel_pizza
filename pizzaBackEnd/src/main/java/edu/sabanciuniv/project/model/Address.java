package edu.sabanciuniv.project.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
public class Address {

	@Id
	private String id;
	@NotBlank
	@Size(max = 20)
	private String country;
	@NotBlank
	@Size(max = 20)
	private String city;
	private String postcode;
	private User user;
	//there is no order module so if the cart is empty->no order
	
	
	public Address(@NotBlank @Size(max = 20) String country, @NotBlank @Size(max = 20) String city, String postcode,
			User user) {
		super();
		this.country = country;
		this.city = city;
		this.postcode = postcode;
		this.user = user;
	}
	public Address() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", country=" + country + ", city=" + city + ", postcode=" + postcode + ", user="
				+ user + "]";
	}
	
	
}
