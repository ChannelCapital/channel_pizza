package com.example.pizzaDeliveryApp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pizzaorder")
public class PizzaOrder {

	@Id
	private String id;
	private String ownerid;
	@Indexed(direction = IndexDirection.ASCENDING)
	private double orderTotalPrice;
	private List<Pizza> orderedPizzas;
	
	public PizzaOrder() {
		super();
	}

	public PizzaOrder(String ownerid, double orderTotalPrice, List<Pizza> orderedPizzas) {
		super();
		this.ownerid = ownerid;
		this.orderTotalPrice = orderTotalPrice;
		this.orderedPizzas = orderedPizzas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
	}

	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public List<Pizza> getOrderedPizzas() {
		return orderedPizzas;
	}

	public void setOrderedPizzas(List<Pizza> orderedPizzas) {
		this.orderedPizzas = orderedPizzas;
	}

	@Override
	public String toString() {
		return "PizzaOrder [id=" + id + ", ownerid=" + ownerid + ", orderTotalPrice=" + orderTotalPrice + "]";
	}
	
	
	
	
}
