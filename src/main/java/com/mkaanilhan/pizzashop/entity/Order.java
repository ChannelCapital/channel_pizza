package com.mkaanilhan.pizzashop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "order")
public class Order {

    @Id
    private String id;
    private String userID;
    private List<Pizza> pizzaID;
    private Date date;
    private double orderTotal;

    public Order(String userID, List<Pizza> pizzaID, Date date, double orderTotal) {
        this.userID = userID;
        this.pizzaID = pizzaID;
        this.date = date;
        this.orderTotal = orderTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public List<Pizza> getPizzaID() {
        return pizzaID;
    }

    public void setPizzaID(List<Pizza> pizzaID) {
        this.pizzaID = pizzaID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }
}
