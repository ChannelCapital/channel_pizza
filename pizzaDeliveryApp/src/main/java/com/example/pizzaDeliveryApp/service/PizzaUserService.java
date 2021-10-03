package com.example.pizzaDeliveryApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pizzaDeliveryApp.model.Pizza;
import com.example.pizzaDeliveryApp.model.PizzaOrder;

import com.example.pizzaDeliveryApp.model.PizzaUser;
import com.example.pizzaDeliveryApp.repository.PizzaRepository;
import com.example.pizzaDeliveryApp.repository.PizzaUserRepository;

/**
 * @author Batuhan Ugur
 * @since 26.09.2021
 */
@Service
public class PizzaUserService {

	@Autowired
	PizzaUserRepository pizzaUserRepository;
	@Autowired
	PizzaRepository pizzaRepository;
	
	public PizzaUserService(PizzaUserRepository pizzaUserRepository, PizzaRepository pizzaRepository) {
		this.pizzaUserRepository = pizzaUserRepository;
		this.pizzaRepository = pizzaRepository;
	}
	
	/**
	 * This method is used for add a specific pizzauser to database.
	 * @param pizzaUser with all attributes.
	 */
	public void addPizzaUser(PizzaUser pizzaUser) {
		
		 pizzaUserRepository.insert(pizzaUser);
		 
	}
	
	/**
	 * This method is used for deleting specific pizzauser from database.
	 * @param pizzauserid for detecting user from database.
	 */
	public void removePizzaUser(String pizzaUserID) {
		
		pizzaUserRepository.deleteById(pizzaUserID);
	}
	
	/**
	 * This method is used for getting all pizzausers from database.
	 * @return List of all pizzausers.
	 */
	public List<PizzaUser> getAllPizzaUsers() {
		return pizzaUserRepository.findAll();
		
	}
	
	/**
	 * This method is updating existing pizzausers attributes.
	 * @param pizzaUser with all attributes.
	 */
	public void updatePizzaUser(PizzaUser pizzaUser) {
		
		Optional<PizzaUser> updatedUser = pizzaUserRepository.findById(pizzaUser.getId());
		updatedUser.get().setUsername(pizzaUser.getUsername());
		updatedUser.get().setPassword(pizzaUser.getPassword());
		updatedUser.get().setUserAdress(pizzaUser.getUserAdress());
		updatedUser.get().setCart(pizzaUser.getCart());
		updatedUser.get().setUserOrders(pizzaUser.getUserOrders());
		pizzaUserRepository.save(pizzaUser);
		
	}
	
	/**
	 * This method is used for getting all pizzaorders from a specific user
	 * @param pizzauserid for detecting user from database.
	 * @return List of all pizzaorders.
	 */
	public List<PizzaOrder> getAllPizzaOrdersbyPizzaUserID(String pizzaUserID){
		
		List<PizzaUser> allPizzaUsers = pizzaUserRepository.findAll();
		List<PizzaOrder> orderList = null;
		
		for (int i = 0; i < allPizzaUsers.size(); i++) {
			
			if(allPizzaUsers.get(i).getId().equals(pizzaUserID)) {
				
				orderList = allPizzaUsers.get(i).getUserOrders();
			}
		}
	
			return orderList;
	}
	

	/**
	 * This method is used for add cart to specific user.
	 * @param pizzauserid for detecting user from database.
	 */
	public void addCarttoUser(String pizzaUserID) {
		
	Optional<PizzaUser> pizzaUser = pizzaUserRepository.findById(pizzaUserID);
	
		pizzaUser.get().setCart(new ArrayList<Pizza>());
	
	
		
	}

	/**
	 * This method is used for getting cart for a specific user
	 * @param pizzauserid for detecting user from database.
	 * @return list of pizzas which exists in cart.
	 */
	public List<Pizza> getCartByPizzaUserID(String pizzaUserID){
		
		List<PizzaUser> allPizzaUsers = pizzaUserRepository.findAll();
		List<Pizza> cart = null;
		
		for (int i = 0; i < allPizzaUsers.size(); i++) {
			
			if(allPizzaUsers.get(i).getId().equals(pizzaUserID)) {
				
				cart = allPizzaUsers.get(i).getCart();
			}
		}
	
			return cart;
	}
		
	/**
	 * This method is used for adding pizza to specific pizzauser's cart.
	 * @param pizzauserid for detecting user from database.
	 * @param pizzaid for detecting pizza from database.
	 */
	public void addPizzaToCart(String pizzaUserID, String pizzaID) {
		
		Optional<PizzaUser> pizzaUser = pizzaUserRepository.findById(pizzaUserID);
		Optional<Pizza> pizza= pizzaRepository.findById(pizzaID);
		pizzaUser.get().getCart().add(pizza.get());
		pizzaUserRepository.save(pizzaUser.get());
		
	}
	
	/**
	 * This method is used for removing pizza from specific pizza from pizzauser's cart.
	 * @param pizzauserid for detecting user from database.
	 * @param pizzaid for detecting pizza from database.
	 */
	public void removePizzaFromCart(String pizzaUserID, String pizzaID) {
		
		Optional<PizzaUser> pizzaUser = pizzaUserRepository.findById(pizzaUserID);
		Optional<Pizza> pizza= pizzaRepository.findById(pizzaID);
		
		for (int i = 0; i < pizzaUser.get().getCart().size() ;i++) {
			if (pizzaUser.get().getCart().get(i).getId().equals(pizza.get().getId())) {
				pizzaUser.get().getCart().remove(i);
				break;
			}
		}

		pizzaUserRepository.save(pizzaUser.get());
	}


	/**
	 * This method is used for comparing pizzauser's information with database to login in order to give order.
	 * @param pizzausers's username attribute
	 * @param pizzausers's password attribute
	 * @return
	 */
	public PizzaUser login(String username, String password) {
		
		List<PizzaUser> allPizzaUsers = pizzaUserRepository.findAll();
		PizzaUser pizzaUser = null;
		
		for (int i = 0; i < allPizzaUsers.size(); i++) {
			
			if (allPizzaUsers.get(i).getUsername().equals(username) && allPizzaUsers.get(i).getPassword().equals(password)) {
				pizzaUser = allPizzaUsers.get(i);
			}
		}
	
		return pizzaUser;
	}
	
	/**
	 * This method is used for getting real time total price of pizzauser's cart.
	 * @param pizzauserid for detecting user from database.
	 * @return double type of total price of existing pizzas in pizzauser's cart.
	 */
	public double getCartTotalPrice(String pizzaUserID) {
		Optional<PizzaUser> pizzaUser = pizzaUserRepository.findById(pizzaUserID);
		List<Pizza> cartList = pizzaUser.get().getCart();
		double orderTotalPrice = 0;
		for (Pizza pizza : cartList) {
			orderTotalPrice += pizza.getPrice();
		}
		return orderTotalPrice;
		
	}
	
	

}
