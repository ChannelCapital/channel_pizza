package com.example.pizzaDeliveryApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pizzaDeliveryApp.model.Pizza;
import com.example.pizzaDeliveryApp.model.PizzaOrder;
import com.example.pizzaDeliveryApp.model.PizzaUser;
import com.example.pizzaDeliveryApp.repository.PizzaOrderRepository;
import com.example.pizzaDeliveryApp.repository.PizzaUserRepository;

/**
 * @author Batuhan Ugur
 * @since 26.09.2021 
 */
@Service
public class PizzaOrderService {

	@Autowired
	PizzaOrderRepository pizzaOrderRepository;
	@Autowired
	PizzaUserRepository pizzaUserRepository;
	
	public PizzaOrderService(PizzaOrderRepository pizzaOrderRepository, PizzaUserRepository pizzaUserRepository) {
		this.pizzaOrderRepository = pizzaOrderRepository;
		this.pizzaUserRepository = pizzaUserRepository;
	}
	
	/**
	 * This method is used for getting all pizzaorders from database.
	 * @return List of all pizzaorders.
	 */
	public List<PizzaOrder> getAllPizzaOrders() {
		
		return pizzaOrderRepository.findAll();

	}
	
	/**
	 * This method is used for adding pizzaorders to database which exists in pizzauser's cart. 
	 * @param pizzauserid for detecting user from database.
	 */
	public void addPizzaOrder(String pizzaUserID) {
		
		Optional<PizzaUser> newPizzaUser = pizzaUserRepository.findById(pizzaUserID);
		List<Pizza> cartList = newPizzaUser.get().getCart();
		double orderTotalPrice = 0;
		
		for (Pizza pizza : cartList) {
			orderTotalPrice += pizza.getPrice();
		}
			
		PizzaOrder pizzaOrder = new PizzaOrder(newPizzaUser.get().getId(), orderTotalPrice,cartList);
		pizzaOrderRepository.insert(pizzaOrder);
		newPizzaUser.get().getUserOrders().add(pizzaOrder);
		newPizzaUser.get().setCart(new ArrayList<>());
		pizzaUserRepository.save(newPizzaUser.get());

	}
	
	/**
	 * This method is used for getting pizzaorders for a specific pizzauser.
	 * @param pizzauserid for detecting user from database.
	 * @return List of pizzaorders which exists in specific pizzauser's orders.
	 */
	public List<PizzaOrder> getPizzaOrdersByPizzaUserID(String pizzaUserID){
		Optional<PizzaUser> PizzaUser = pizzaUserRepository.findById(pizzaUserID);
		List<PizzaOrder> orderList = PizzaUser.get().getUserOrders();
				
		
		return orderList;
	}
	
}
