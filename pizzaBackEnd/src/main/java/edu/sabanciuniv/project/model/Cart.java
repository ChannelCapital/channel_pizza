package edu.sabanciuniv.project.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Cart {

	@Id
	private String id; 
	private int quantity;
	private User user;
	private Product product;
	
	public Cart() {
		super();
	}
	

	public Cart(int quantity) {
		super();
		this.quantity = quantity;
	}


	public Cart(int quantity, User user, Product product) {
		super();
		this.quantity = quantity;
		this.user = user;
		this.product = product;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
