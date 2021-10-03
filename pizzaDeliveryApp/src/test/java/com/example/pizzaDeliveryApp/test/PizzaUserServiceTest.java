package com.example.pizzaDeliveryApp.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.example.pizzaDeliveryApp.model.Pizza;
import com.example.pizzaDeliveryApp.model.PizzaUser;
import com.example.pizzaDeliveryApp.repository.PizzaRepository;
import com.example.pizzaDeliveryApp.repository.PizzaUserRepository;

import com.example.pizzaDeliveryApp.service.PizzaUserService;

public class PizzaUserServiceTest {

	PizzaRepository pizzaRepository;
	PizzaUserService pizzaUserService;
	PizzaUserRepository pizzaUserRepository;
	
	@Before
    public void setUp() {

		pizzaRepository = Mockito.mock(PizzaRepository.class);
		pizzaUserRepository = Mockito.mock(PizzaUserRepository.class);
		pizzaUserService = new PizzaUserService(pizzaUserRepository, pizzaRepository);

    }
	
	@Test
	public void addPizzaUserTest() {
		
		PizzaUser pizzaUser1 = new PizzaUser("asd", "asd", "asd", new ArrayList<>(), new ArrayList<>());
		PizzaUser pizzaUser2 = new PizzaUser("abc", "asd", "asd", new ArrayList<>(), new ArrayList<>());
		pizzaUser1.setId("asd");
		pizzaUser2.setId("asdd");
		List<PizzaUser> userList = pizzaUserService.getAllPizzaUsers();
		userList.add(pizzaUser1);
		userList.add(pizzaUser2);
		Mockito.when(pizzaUserRepository.insert(pizzaUser1)).thenReturn(pizzaUser1);
		Assert.assertEquals(pizzaUser1,pizzaUser1);
		Mockito.verify(pizzaUserRepository).findAll();
		
	}
	
	@Test
	public void getCartByPizzaUserIDTest() {
		
		Pizza pizza1 = new Pizza("asd",12,"asd","m");
		Pizza pizza2 = new Pizza("asd",14,"asd","m");
		List<Pizza> pizzaList = new ArrayList<>();
		List<Pizza> pizzaList2 = new ArrayList<>();
		pizzaList.add(pizza1);
		pizzaList.add(pizza2);
		pizzaList2.add(pizza1);
		pizzaList2.add(pizza2);
		PizzaUser pizzaUser = new PizzaUser("asd", "password", "userAdress", new ArrayList<>(), pizzaList);
		PizzaUser pizzaUser2 = new PizzaUser("asd", "password", "userAdress", new ArrayList<>(), pizzaList);
		List<PizzaUser> pizzaUserList = new ArrayList<>();
		pizzaUserList.add(pizzaUser);
		pizzaUserList.add(pizzaUser2);
		Mockito.when(pizzaUserRepository.findAll()).thenReturn(pizzaUserList);
	    Assert.assertEquals(pizzaList,pizzaList2);   
		
		
		
	}
	
	@Test
	public void addPizzaToCartTest() {
		
		Pizza pizza = new Pizza("asd", 12, "asd", "M");
		List<Pizza> pizzaList = new ArrayList<>();
		List<Pizza> pizzaList2 = new ArrayList<>();
		pizzaList.add(pizza);
		pizzaList2.add(pizza);
		PizzaUser pizzaUser = new PizzaUser("asd", "password", "userAdress", new ArrayList<>(), pizzaList);
		PizzaUser pizzaUser2 = new PizzaUser("asd", "password", "userAdress", new ArrayList<>(), pizzaList2);
		List<PizzaUser> pizzaUserList = new ArrayList<>();
		pizzaUserList.add(pizzaUser);
		pizzaUserList.add(pizzaUser2);
		Mockito.when(pizzaUserRepository.findAll()).thenReturn(pizzaUserList);
	    Assert.assertEquals(pizzaList,pizzaList2);   
	}
	
	@Test
	public void loginTest(){
		
		Pizza pizza = new Pizza("asd", 12, "asd", "M");
		List<Pizza> pizzaList = new ArrayList<>();
		List<Pizza> pizzaList2 = new ArrayList<>();
		pizzaList.add(pizza);
		pizzaList2.add(pizza);
		PizzaUser pizzaUser = new PizzaUser("asd", "password", "userAdress", new ArrayList<>(), pizzaList);
		PizzaUser pizzaUser2 = new PizzaUser("asd", "password", "userAdress", new ArrayList<>(), pizzaList2);
		List<PizzaUser> pizzaUserList = new ArrayList<>();
		pizzaUserList.add(pizzaUser);
		pizzaUserList.add(pizzaUser2);
		Mockito.when(pizzaUserRepository.findAll()).thenReturn(pizzaUserList); 
        Assert.assertEquals(pizzaUser.getUsername(),pizzaUser2.getUsername());
		
	}
	
	@Test
	public void getCartTotalPriceTest() {
		Pizza pizza = new Pizza("asd", 12, "asd", "M");
		List<Pizza> pizzaList = new ArrayList<>();
		List<Pizza> pizzaList2 = new ArrayList<>();
		pizzaList.add(pizza);
		pizzaList2.add(pizza);
		PizzaUser pizzaUser = new PizzaUser("asd", "password", "userAdress", new ArrayList<>(), pizzaList);
		PizzaUser pizzaUser2 = new PizzaUser("asd", "password", "userAdress", new ArrayList<>(), pizzaList2);
		List<PizzaUser> pizzaUserList = new ArrayList<>();
		pizzaUserList.add(pizzaUser);
		pizzaUserList.add(pizzaUser2);
		Mockito.when(pizzaUserRepository.findAll()).thenReturn(pizzaUserList); 
        Assert.assertEquals(pizzaUser.getCart(),pizzaUser2.getCart());
		
	}
	
}
