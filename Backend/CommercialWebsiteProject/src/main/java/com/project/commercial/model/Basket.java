package com.project.commercial.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

/**
 * When user add product to basket a basket object is created. It includes the feature below.
 * When an order is given the basket object will be deleted from the basket.
 * @author fatih akguc
 */

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Document(collection = "Basket")
public class Basket {
	
	@Transient
	public static final String SEQUENCE_NAME = "basket_sequence";
	
	@Id
	private int id;
	private String name;
	private String ingredients;
	private String size;
	private Double price;
	private int quantity;
	private long userId;
	
	
	public Basket() {
		super();
	}


	public Basket(String name, String ingredients, String size, Double price, int quantity) {
		super();
		this.name = name;
		this.ingredients = ingredients;
		this.size = size;
		this.price = price;
		this.quantity = quantity;
	}


	public Basket(int id, String name, String ingredients, String size, Double price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.size = size;
		this.price = price;
		this.quantity = quantity;
	}
	
	


	public Basket(int id, String name, String ingredients, String size, Double price, int quantity, Integer userId) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.size = size;
		this.price = price;
		this.quantity = quantity;
		this.userId = userId;
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getIngredients() {
		return ingredients;
	}



	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public long getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

/*
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}


	@Override
	public String toString() {
		return "Basket [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", size=" + size + ", price="
				+ price + ", quantity=" + quantity + "]";
	}

*/
	











	

}
