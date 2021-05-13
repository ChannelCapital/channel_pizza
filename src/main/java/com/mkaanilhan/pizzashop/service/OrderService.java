package com.mkaanilhan.pizzashop.service;

import com.mkaanilhan.pizzashop.entity.pizzaListResponse;
import com.mkaanilhan.pizzashop.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrderById(String userID);

    List<Order> getAllOrder();

    void addOrder(pizzaListResponse pizzaListResponse);

}
