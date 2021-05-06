package com.mkaanilhan.pizzashop.entity;

public class ApiResponse {

    private String userID;
    private String pizzaID;

    public ApiResponse(String userID, String pizzaID) {
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
