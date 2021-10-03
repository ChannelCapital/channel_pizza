package com.example.pizzaDeliveryApp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pizza")
public class Pizza {

	@Id
	private String id;
	@Indexed(direction = IndexDirection.ASCENDING)
	private String name;
	private int price;
	private String image;
	private String size;
	
	public Pizza() {
		super();
	}

	public Pizza(String name, int price, String image, String size) {
		super();
		this.name = name;
		this.price = price;
		this.image = image;
		this.size = size;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
