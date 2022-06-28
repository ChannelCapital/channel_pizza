package edu.sabanciuniv.project.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document
public class OrderItem {

	@Id
	private String id;
	private LocalDateTime createdDate;
	private Product product;
	private int quantity;
	private double price;
	@JsonIgnore
	private Order order;
	
	public OrderItem() {
		super();
	}




	public OrderItem(LocalDateTime createdDate, Product product, int quantity, double price, Order order) {
		super();
		this.createdDate = createdDate;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
		this.order = order;
	}




	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "OrderItems [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
