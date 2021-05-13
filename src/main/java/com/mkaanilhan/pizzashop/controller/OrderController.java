package com.mkaanilhan.pizzashop.controller;

import com.mkaanilhan.pizzashop.entity.pizzaListResponse;
import com.mkaanilhan.pizzashop.entity.Order;
import com.mkaanilhan.pizzashop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/addorder")
    public  void addOrder(@RequestBody pizzaListResponse pizzaListResponse) {

        orderService.addOrder(pizzaListResponse);

    }

    //ordersbyid
    @GetMapping("orderbyid/{userID}")
    public List<Order> getOrderById(@PathVariable("userID") String userID){

        return orderService.getOrderById(userID);

    }

    //all
    @GetMapping("/allorder")
    public List<Order> getAllOrder(){

        return orderService.getAllOrder();
    }

}
