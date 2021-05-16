package com.mkaanilhan.pizzashop.entity;

import java.util.List;

public class PizzaResponse {


    private String userID;
    private String pizzaID;

    public PizzaResponse(){

        super();

    }

    public PizzaResponse(String userID, String pizzaID) {
        this.userID = userID;
        this.pizzaID = pizzaID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPizzaID() {
        return pizzaID;
    }

    public void setPizzaID(String pizzaID) {
        this.pizzaID = pizzaID;
    }
}
