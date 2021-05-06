package com.mkaanilhan.pizzashop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "order")
public class Order {

    @Id
    private String id;
    private String userID;
    private Pizza pizza;
    private Date date;

    public Order(String userID, Pizza pizza, Date date) {
        this.userID = userID;
        this.pizza = pizza;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return userID;
    }

    public void setUser(String userID) {
        this.userID = userID;
    }

    public Pizza getMeal() {
        return pizza;
    }

    public void setMeal(Pizza pizza) {
        this.pizza = pizza;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }
}
