package com.project.commercial.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

/**
 * After the pizza is selected, an order object is generated with the
 * features below.
 * @author fatih akguc
 * @param totalCost the total cost of the products in the basket
 * @param basket the list of the product in the basket and ready to order.
 */

@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Document(collection = "Order")
public class Order {
	
	@Transient
	public static final String SEQUENCE_NAME = "order_sequence";
	
	@Id
	private int id;
	private double tatalCost; 
	private List <Basket> basket;
	
	
	public Order() {
		super();
	}


	public Order(int id, double tatalCost, List<Basket> basket) {
		super();
		this.id = id;
		this.tatalCost = tatalCost;
		this.basket = basket;
	}


	public Order( double tatalCost, List<Basket> basket) {
		super();
		this.tatalCost = tatalCost;
		this.basket = basket;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public double getTatalCost() {
		return tatalCost;
	}


	public void setTatalCost(double tatalCost) {
		this.tatalCost = tatalCost;
	}

	public List<Basket> getBasket() {
		return basket;
	}


	public void setBasket(List<Basket> basket) {
		this.basket = basket;
	}

/*
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", tatalCost=" + tatalCost + ", basket=" + basket + "]";
	}

*/
}
