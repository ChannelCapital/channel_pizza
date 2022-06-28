package edu.sabanciuniv.project.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "products")
public class Product {

	@Id
	private String id;
	private String name;
	private double price;
	private String description;
	private String imageURL;
	private boolean published;

	private List<Cart> carts;

	public Product(String name, String description, boolean published) {
		super();
		this.name = name;
		this.description = description;
		this.published = published;
	}

	public Product(String name, double price, String description, String imageURL, boolean published) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageURL = imageURL;
		this.published = published;
	}
	
	public Product() {
		super();
	}

	public String getId() {
		return id;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", imageURL=" + imageURL + "]";
	}

}
