package com.example.pizzaDeliveryApp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pizzauser")
public class PizzaUser {

	@Id
	private String id;
	@Indexed(direction = IndexDirection.ASCENDING)
	private String username;
	private String password;
	private String userAdress;
	private List<PizzaOrder> userOrders;
	private List<Pizza> cart;
	
	public PizzaUser() {
		super();
	}

	public PizzaUser(String username, String password, String userAdress, List<PizzaOrder> userOrders,
			List<Pizza> cart) {
		super();
		this.username = username;
		this.password = password;
		this.userAdress = userAdress;
		this.userOrders = userOrders;
		this.cart = cart;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserAdress() {
		return userAdress;
	}

	public void setUserAdress(String userAdress) {
		this.userAdress = userAdress;
	}

	public List<PizzaOrder> getUserOrders() {
		return userOrders;
	}

	public void setUserOrders(List<PizzaOrder> userOrders) {
		this.userOrders = userOrders;
	}

	public List<Pizza> getCart() {
		return cart;
	}

	public void setCart(List<Pizza> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "PizzaUser [id=" + id + ", username=" + username + "]";
	}
	
	
	
	
}
