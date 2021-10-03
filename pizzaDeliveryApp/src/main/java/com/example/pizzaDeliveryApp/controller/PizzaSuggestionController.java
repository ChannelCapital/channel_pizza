package com.example.pizzaDeliveryApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzaDeliveryApp.model.PizzaSuggestion;
import com.example.pizzaDeliveryApp.service.PizzaSuggestionService;

@RestController
@RequestMapping("/suggestions")
@CrossOrigin("http://localhost:8081/")
public class PizzaSuggestionController {

	@Autowired
	PizzaSuggestionService pizzaSuggestionService;
	
	@PostMapping("/addsuggestion")
	public void addSuggestion(@RequestBody PizzaSuggestion pizzaSuggestion) {
		
		pizzaSuggestionService.addSuggestion(pizzaSuggestion);
		
	}
	
	
	
}
