package com.channelpizza.webapp.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OrderItem {

    private int quantity;
    private double pizzaPrice;
    private String pizzaId;

    public OrderItem() {
    }

    public OrderItem(int quantity, double pizzaPrice, String pizzaId) {
        this.quantity = quantity;
        this.pizzaPrice = pizzaPrice;
        this.pizzaId = pizzaId;
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

}
