package com.example.pizzaDeliveryApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pizzaDeliveryApp.model.PizzaSuggestion;
import com.example.pizzaDeliveryApp.repository.PizzaSuggestionRepository;


/**
 * @author Batuhan Ugur
 * @since 26.09.2021
 */
@Service
public class PizzaSuggestionService {

	@Autowired
	PizzaSuggestionRepository pizzaSuggestionRepository;

	public PizzaSuggestionService(PizzaSuggestionRepository pizzaSuggestionRepository) {
		
		this.pizzaSuggestionRepository = pizzaSuggestionRepository;
	}
	
	/**
	 * This method is used for adding suggestions to database.
	 * @param PizzaSuggestion item that contains name, email and subject from client.
	 */
	public void addSuggestion(PizzaSuggestion pizzaSuggestion) {
		pizzaSuggestionRepository.insert(pizzaSuggestion);
		
	}
	
	
}
