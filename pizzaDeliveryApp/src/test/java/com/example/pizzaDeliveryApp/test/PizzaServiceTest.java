package com.example.pizzaDeliveryApp.test;


import org.mockito.Mockito;


import com.example.pizzaDeliveryApp.model.Pizza;
import com.example.pizzaDeliveryApp.repository.PizzaRepository;
import com.example.pizzaDeliveryApp.service.PizzaService;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;




public class PizzaServiceTest {
	
	
	
	
	PizzaRepository pizzaRepository;
	PizzaService pizzaService;
	
	 @Before
	    public void setUp() {

	        pizzaRepository = Mockito.mock(PizzaRepository.class);
	        pizzaService = new PizzaService(pizzaRepository);

	    }
	
	
	@Test
	public void getAllPizzasTest(){
		Pizza pizzatest = new Pizza("asd",12,"asd","sada");

        List<Pizza> pizzaList =  new ArrayList<>();
        List<Pizza> pizzas = pizzaService.getAllPizzas();
        pizzaList.add(pizzatest);
        pizzas.add(pizzatest);

        Mockito.when(pizzaRepository.findAll()).thenReturn(pizzaList);
        Assert.assertEquals(pizzaList,pizzas);
        Mockito.verify(pizzaRepository).findAll();
	}
	
	@Test
	public void removePizzaTest() {
		 Optional<Pizza> pizza = pizzaRepository.findById("id");
	    
		pizzaRepository.deleteById("id");
		
		Optional<Pizza> deletedPizza = pizzaRepository.findById("id");
		
		
	    Assert.assertEquals(pizza, deletedPizza);
		
	}
	
	

}
