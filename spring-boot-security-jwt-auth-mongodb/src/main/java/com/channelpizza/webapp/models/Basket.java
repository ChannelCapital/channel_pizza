package com.channelpizza.webapp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "baskets")
public class Basket {

    @Id
    private String id;

    private String userId;

    @Field("basketItem")
    private ArrayList<OrderItem> basketItem;

    public Basket() {
    }

    public Basket(String userId, ArrayList<OrderItem> basketItem) {
        this.userId = userId;
        this.basketItem = basketItem;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public List<OrderItem> getOrderItem() {
        return basketItem;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setOrderItem(ArrayList<OrderItem> basketItem) {
        this.basketItem = basketItem;
    }

}
