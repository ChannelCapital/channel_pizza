package com.example.pizzaDeliveryApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzaDeliveryApp.model.PizzaOrder;

import com.example.pizzaDeliveryApp.service.PizzaOrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin("http://localhost:8081/")
public class PizzaOrderController {

	@Autowired
	PizzaOrderService pizzaOrderService;
	
	@GetMapping("/getallpizzaorders")
	public List<PizzaOrder> getAllPizzaOrders() {
		
		return pizzaOrderService.getAllPizzaOrders();

	}
	
	@PostMapping("/addpizzaorder/{pizzauserid}")
	public void addPizzaOrder(@PathVariable("pizzauserid") String pizzauserid) {
		
		pizzaOrderService.addPizzaOrder(pizzauserid);

	}
	
	@GetMapping("/getpizzaordersbypizzauserid/{pizzauserid}")
	public List<PizzaOrder> getPizzaOrdersByPizzaUserID(@PathVariable("pizzauserid") String pizzauserid){
		
		return pizzaOrderService.getPizzaOrdersByPizzaUserID(pizzauserid);
		
		 
		
	}
	
}
