package com.example.pizzaDeliveryApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzaDeliveryApp.model.Pizza;
import com.example.pizzaDeliveryApp.service.PizzaService;

@RestController
@RequestMapping("/pizzas")
@CrossOrigin("http://localhost:8081/")
public class PizzaController {

	@Autowired
	PizzaService pizzaService;
	
	@GetMapping("/allpizzas")
	public List<Pizza> getAllPizzas(){
		
		return pizzaService.getAllPizzas();
	}
	
	@PostMapping("/removepizza")
	public void removePizza(@RequestBody Pizza pizza) {
		
		pizzaService.removePizza(pizza);
	}
	
	
}
