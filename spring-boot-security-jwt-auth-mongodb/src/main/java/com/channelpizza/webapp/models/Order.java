package com.channelpizza.webapp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "orders")
public class Order {

    @Id
    private String id;


    private String userId;


    @Field("orderItem")
    private ArrayList<OrderItem> orderItem;

    public Order() {
    }

    public Order(String userId, ArrayList<OrderItem> orderItem) {
        this.userId = userId;
        this.orderItem = orderItem;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setOrderItem(ArrayList<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }
}
