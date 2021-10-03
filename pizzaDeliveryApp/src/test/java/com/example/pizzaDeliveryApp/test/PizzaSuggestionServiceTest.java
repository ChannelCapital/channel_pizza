package com.example.pizzaDeliveryApp.test;


import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


import com.example.pizzaDeliveryApp.model.PizzaSuggestion;

import com.example.pizzaDeliveryApp.repository.PizzaSuggestionRepository;

import com.example.pizzaDeliveryApp.service.PizzaSuggestionService;


public class PizzaSuggestionServiceTest {

	PizzaSuggestionRepository pizzaSuggestionRepository;
	PizzaSuggestionService pizzaSuggestionService;
	
	@Before
    public void setUp() {

		pizzaSuggestionRepository = Mockito.mock(PizzaSuggestionRepository.class);
		pizzaSuggestionService = new PizzaSuggestionService(pizzaSuggestionRepository);

    }
	
	@Test
	public void addSuggestionTest() {
		
        
        PizzaSuggestion suggestion = new PizzaSuggestion("asd", "email", "subject");
        PizzaSuggestion suggestion2 = new PizzaSuggestion("asd", "email", "subject");
        List<PizzaSuggestion> sugList = pizzaSuggestionRepository.findAll();
        sugList.add(suggestion);
        sugList.add(suggestion2);
        Mockito.when(pizzaSuggestionRepository.insert(suggestion)).thenReturn(suggestion);
		Assert.assertEquals(suggestion,suggestion2);
		Mockito.verify(pizzaSuggestionRepository).findAll();
        
        
		
	}
	
}
