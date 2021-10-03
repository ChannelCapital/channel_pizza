package com.example.pizzaDeliveryApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.pizzaDeliveryApp.model.Pizza;
import com.example.pizzaDeliveryApp.repository.PizzaRepository;

/**
 * @author Batuhan Ugur
 * @since 26.09.2021
 *
 */
@Service
public class PizzaService {

	@Autowired
	PizzaRepository pizzaRepository;

	
	public PizzaService(PizzaRepository pizzaRepository) {
		this.pizzaRepository = pizzaRepository;
	}
	
	
	/**
	 * This method is used for getting all pizzas from database.
	 * @return all pizzas with all attributes.
	 */
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.findAll();
		
	}
	
	/**
	 * This method is used for deleting specific pizza from database.
	 * @param specific pizza for removing
	 */
	public void removePizza(Pizza pizza) {
		pizzaRepository.delete(pizza);
		
	}
}
