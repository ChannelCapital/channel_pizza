package com.example.pizzaDeliveryApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pizzaDeliveryApp.model.Pizza;
import com.example.pizzaDeliveryApp.model.PizzaOrder;
import com.example.pizzaDeliveryApp.model.PizzaUser;
import com.example.pizzaDeliveryApp.service.PizzaUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:8081/")
public class PizzaUserController {

	@Autowired
	PizzaUserService pizzaUserService;
	
	@PostMapping("/addpizzauser")
	public void addPizzaUser(@RequestBody PizzaUser pizzaUser) {
	pizzaUser.setCart(new ArrayList<Pizza>());
	pizzaUser.setUserOrders(new ArrayList<>());	
	pizzaUserService.addPizzaUser(pizzaUser);
	}

	@PostMapping("/removepizzauser/{pizzauserid}")
	public void removePizzaUser(@PathVariable String pizzauserid) {
		
		pizzaUserService.removePizzaUser(pizzauserid);
	}
	
	@GetMapping("/allpizzausers")
	public List<PizzaUser> getAllPizzaUsers() {
		return pizzaUserService.getAllPizzaUsers();
	}
	
	@PostMapping("/updatepizzauser")
	public void updateUser(@RequestBody PizzaUser pizzaUser) {
		pizzaUserService.updatePizzaUser(pizzaUser);
	}
	
	@GetMapping("/getallpizzaordersbypizzauserid/{pizzauserid}")
	public List<PizzaOrder> getAllPizzaOrdersbyPizzaUserID(@PathVariable String pizzauserid) {
		
		return pizzaUserService.getAllPizzaOrdersbyPizzaUserID(pizzauserid);

	}
	
	@PostMapping("/addcarttouser/{pizzauserid}")
	public void addCarttoUser(@PathVariable String pizzauserid) {
		
		pizzaUserService.addCarttoUser(pizzauserid);
	}
	
	@GetMapping("/getcartbypizzauserid/{pizzauserid}")
	public List<Pizza> getCartByPizzaUserID(@PathVariable String pizzauserid) {
		return pizzaUserService.getCartByPizzaUserID(pizzauserid);

	}
	
	@PostMapping("/addpizzatocart/{pizzauserid}/{pizzaid}")
	public void addPizzaToCart(@PathVariable("pizzauserid") String pizzauserid, @PathVariable("pizzaid") String pizzaid) {
		
		pizzaUserService.addPizzaToCart(pizzauserid, pizzaid);

	}
	
	@PostMapping("/removepizzafromcart/{pizzauserid}/{pizzaid}")
	public void removePizzaFromCart(@PathVariable("pizzauserid") String pizzauserid, @PathVariable("pizzaid") String pizzaid) {
		
		pizzaUserService.removePizzaFromCart(pizzauserid, pizzaid);

	}
	
	@PostMapping("/login/{username}/{password}")
	public PizzaUser login(@PathVariable("username") String username, @PathVariable("password") String password) {
		
		return pizzaUserService.login(username, password);
		

	}
	
	@GetMapping("/getordertotalprice/{pizzauserid}")
		public double getOrderTotalPrice(@PathVariable("pizzauserid") String pizzauserid) {
			return pizzaUserService.getCartTotalPrice(pizzauserid);
		}
	
	
}
