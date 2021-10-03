package com.example.pizzaDeliveryApp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.pizzaDeliveryApp.model.Pizza;
import com.example.pizzaDeliveryApp.model.PizzaOrder;
import com.example.pizzaDeliveryApp.model.PizzaSuggestion;
import com.example.pizzaDeliveryApp.model.PizzaUser;
import com.example.pizzaDeliveryApp.repository.PizzaOrderRepository;
import com.example.pizzaDeliveryApp.repository.PizzaRepository;
import com.example.pizzaDeliveryApp.repository.PizzaSuggestionRepository;
import com.example.pizzaDeliveryApp.repository.PizzaUserRepository;

@Component
public class DbSeeder implements CommandLineRunner {

public PizzaRepository pizzaRepository;
public PizzaOrderRepository pizzaOrderRepository;
public PizzaUserRepository pizzaUserRepository;
public PizzaSuggestionRepository pizzaSuggestionRepository;	
	
	
	public DbSeeder(PizzaRepository pizzaRepository, PizzaOrderRepository pizzaOrderRepository,
		PizzaUserRepository pizzaUserRepository, PizzaSuggestionRepository pizzaSuggestionRepository) {
	this.pizzaRepository = pizzaRepository;
	this.pizzaOrderRepository = pizzaOrderRepository;
	this.pizzaUserRepository = pizzaUserRepository;
	this.pizzaSuggestionRepository = pizzaSuggestionRepository;
}



	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		this.pizzaRepository.deleteAll();
		this.pizzaUserRepository.deleteAll();
		this.pizzaOrderRepository.deleteAll();
		this.pizzaSuggestionRepository.deleteAll();
		
		Pizza Margarita = new Pizza("Margarita Pizza",12,"https://cdn.yemek.com/mnresize/940/940/uploads/2014/09/pizza-margherita-tarifi-yeni-2018.jpg","Medium");
		Pizza Fungi = new Pizza("Fungi Pizza",15,"https://www.yemek24.com/wp-content/uploads/2020/05/Mantarli-Pizza.jpg","Large");
		Pizza Pepperoni = new Pizza("Pepperoni Pizza",14,"https://www.yemek24.com/wp-content/uploads/2020/05/Salamli-Pizza-Tarifi.jpg","Large");
		Pizza BBQChicken = new Pizza("BBQ Chicken Pizza",12,"https://www.oxygenmag.com/wp-content/uploads/2020/05/slide-9-bbqchickenpizza-1.jpg","Medium");
		Pizza Hawaiian = new Pizza("Hawaiian Pizza",15,"https://www.ezcater.com/lunchrush/wp-content/uploads/sites/2/2017/10/shutterstock_313437680.jpg.webp","Large");
		Pizza Supreme = new Pizza("Supreme Pizza", 17, "https://www.ezcater.com/lunchrush/wp-content/uploads/sites/2/2017/10/shutterstock_244706695.jpg.webp","Medium");
		Pizza Buffalo = new Pizza("Buffalo Pizza",10,"https://www.ezcater.com/lunchrush/wp-content/uploads/sites/2/2017/10/Buffalo-Chicken-Pizza-1-1024x683.jpg.webp","Small");
		List<Pizza> allPizzas = Arrays.asList(Margarita,Fungi,Pepperoni,BBQChicken,Hawaiian,Supreme,Buffalo);
		
		List<Pizza> cart = new ArrayList<Pizza>();
		cart.add(Margarita);
		PizzaUser pizzaUser = new PizzaUser("batuhanugur94@gmail.com","123","Acibadem", new ArrayList<PizzaOrder>(), cart);
		
		PizzaOrder pizzaOrder = new PizzaOrder("613f6a48ef3bf074ace661c2", 10, allPizzas);
		
		PizzaSuggestion pizzaSuggestion = new PizzaSuggestion("batuhan","batuhanugur94@gmail.com","Good Service");
		
		
		this.pizzaRepository.saveAll(allPizzas);
		this.pizzaUserRepository.save(pizzaUser);
		this.pizzaOrderRepository.save(pizzaOrder);
		this.pizzaSuggestionRepository.save(pizzaSuggestion);
		
	}

	
	
}
