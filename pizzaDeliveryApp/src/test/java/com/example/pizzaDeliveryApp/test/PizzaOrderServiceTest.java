package com.example.pizzaDeliveryApp.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.example.pizzaDeliveryApp.model.Pizza;
import com.example.pizzaDeliveryApp.model.PizzaOrder;
import com.example.pizzaDeliveryApp.repository.PizzaOrderRepository;

import com.example.pizzaDeliveryApp.repository.PizzaUserRepository;
import com.example.pizzaDeliveryApp.service.PizzaOrderService;


public class PizzaOrderServiceTest {

	
	PizzaOrderRepository pizzaOrderRepository;
	PizzaOrderService pizzaOrderService;
	PizzaUserRepository pizzaUserRepository;
	
	@Before
    public void setUp() {

		pizzaOrderRepository = Mockito.mock(PizzaOrderRepository.class);
		pizzaOrderService = new PizzaOrderService(pizzaOrderRepository,pizzaUserRepository);

    }
	
	@Test
	public void getAllPizzaOrdersTest() {
		
		PizzaOrder pizzaOrder = new PizzaOrder("asd", 14, new ArrayList<>());
		List<PizzaOrder> pizzaOrders = new ArrayList<PizzaOrder>();
		List<PizzaOrder> pizzaOrdersTestList = pizzaOrderService.getAllPizzaOrders();
		pizzaOrders.add(pizzaOrder);
		pizzaOrdersTestList.add(pizzaOrder);
		
		Mockito.when(pizzaOrderRepository.findAll()).thenReturn(pizzaOrders);
		Assert.assertEquals(pizzaOrders, pizzaOrdersTestList);
		Mockito.verify(pizzaOrderRepository).findAll();
		
		
	}
	
	@Test
	public void addPizzaOrderTest() {
		
		Pizza pizza = new Pizza("asd", 12, "asd", "M");
		List<Pizza> pizzaList = new ArrayList<>();
		pizzaList.add(pizza);
		PizzaOrder pizzaOrder = new PizzaOrder("abc", 12, pizzaList);
		List<PizzaOrder> orders = new ArrayList<>();
		orders.add(pizzaOrder);
		List<PizzaOrder> orders2 = pizzaOrderRepository.findAll();
		orders.add(pizzaOrder);	
		Mockito.when(pizzaOrderRepository.insert(orders)).thenReturn(orders);
		Assert.assertEquals(orders,orders2);
		Mockito.verify(pizzaOrderRepository).findAll();
		
	}
	
	@Test
	public void getPizzaOrderByPizzaUserIDTest() {
		
		Pizza pizza = new Pizza("asd",12,"asd","M");      
        List<Pizza> pizzaList =  new ArrayList<>();
        pizzaList.add(pizza);
        PizzaOrder pizzaOrder = new PizzaOrder("abc", 12, pizzaList);
        List<PizzaOrder> pizzaOrder2=  new ArrayList<>();
        pizzaOrder2.add(pizzaOrder);
        List<PizzaOrder> pizzaorders = pizzaOrderService.getAllPizzaOrders();
        pizzaorders.add(pizzaOrder);
        Mockito.when(pizzaOrderRepository.findAll()).thenReturn(pizzaOrder2);
        Assert.assertEquals(pizzaOrder2,pizzaorders);
        Mockito.verify(pizzaOrderRepository).findAll();
		
		
	}
	
	
}
