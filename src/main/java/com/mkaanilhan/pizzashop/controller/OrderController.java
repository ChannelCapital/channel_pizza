package com.mkaanilhan.pizzashop.controller;

import com.mkaanilhan.pizzashop.entity.ApiResponse;
import com.mkaanilhan.pizzashop.entity.Order;
import com.mkaanilhan.pizzashop.entity.User;
import com.mkaanilhan.pizzashop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/addorder")
    public void addOrder(@RequestBody ApiResponse apiResponse) {

        orderService.addOrder(apiResponse);

    }

    @GetMapping("ordersbyid/{userID}")
    public List<Order> getUserOrderById(@PathVariable("userID") String userID){

        return orderService.getUserOrderById(userID);

    }

    @GetMapping("/all")
    public List<Order> getOrders(){

        return orderService.getOrders();
    }

}
