package com.mkaanilhan.pizzashop.service;

import com.mkaanilhan.pizzashop.entity.ApiResponse;
import com.mkaanilhan.pizzashop.entity.Order;

import java.util.List;

public interface OrderService {

    void addOrder(ApiResponse apiResponse);

    List<Order> getUserOrderById(String userID);

    List<Order> getOrders();
}
