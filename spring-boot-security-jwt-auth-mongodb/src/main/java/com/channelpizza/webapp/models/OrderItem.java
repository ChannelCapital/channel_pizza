package com.channelpizza.webapp.models;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity for each item in an order
 * @author Melih Yayli
 */
@Document
public class OrderItem {

    private int quantity;
    private double pizzaPrice;
    private String pizzaId;
    private String pizzaName;

    public OrderItem() {
    }

    public OrderItem(int quantity, double pizzaPrice, String pizzaId, String pizzaName) {
        this.quantity = quantity;
        this.pizzaPrice = pizzaPrice;
        this.pizzaId = pizzaId;
        this.pizzaName = pizzaName;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public String getPizzaName() {
        return pizzaName;
    }
}
