package com.mkaanilhan.pizzashop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String email;
    private String password;
    private String address;
    private List<Order> orders;
    private List<Pizza> favorites;
    private List<Pizza> basket;


    public User(String email, String password, String address, List<Order> orders, List<Pizza> favorites, List<Pizza> basket) {
        this.email = email;
        this.password = password;
        this.address = address;
        this.orders = orders;
        this.favorites = favorites;
        this.basket = basket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Pizza> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Pizza> favorites) {
        this.favorites = favorites;
    }

    public List<Pizza> getBasket() {
        return basket;
    }

    public void setBasket(List<Pizza> basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
