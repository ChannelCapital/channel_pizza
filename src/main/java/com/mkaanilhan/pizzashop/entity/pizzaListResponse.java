package com.mkaanilhan.pizzashop.entity;

import java.util.List;

public class pizzaListResponse {

    private String userID;
    private List<Pizza> pizzaID;

    public pizzaListResponse(String userID, List<Pizza> pizzaID) {
        this.userID = userID;
        this.pizzaID = pizzaID;
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
}
