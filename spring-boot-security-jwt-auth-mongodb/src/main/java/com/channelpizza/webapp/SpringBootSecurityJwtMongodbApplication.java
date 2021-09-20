package com.channelpizza.webapp;

import com.channelpizza.webapp.models.Pizza;
import com.channelpizza.webapp.repository.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class SpringBootSecurityJwtMongodbApplication {
//https://www.bezkoder.com/spring-boot-jwt-auth-mongodb/
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwtMongodbApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PizzaRepository pizzaRepository, MongoTemplate mongoTemplate) {
		return args -> {
			Pizza pizza1 = new Pizza(
					"Margherita",2.5,"tomatoes, mozzarella, basil, olive oil","http://simply-delicious-food.com/wp-content/uploads/2020/06/Grilled-Pizza-Margherita-3.jpg"
			);
			Pizza pizza2 = new Pizza(
					"Pepperoni",2.75,"pepperoni, mozzarela, oregano","https://www.simplyrecipes.com/thmb/HQJVIRwkySb-QiYv6saC9dEjU5A=/960x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__09__easy-pepperoni-pizza-lead-4-82c60893fcad4ade906a8a9f59b8da9d.jpg"
			);
			Pizza pizza3 = new Pizza(
					"Calzone",3.99,"ricotta, mozzarella, salami, parmesan","https://www.happyfoodstube.com/wp-content/uploads/2016/03/calzone-pizza-picture.jpg"
			);
			Pizza pizza4 = new Pizza(
					"Quattro Formaggi",5.05,"mozzarella, gorgonzola, ricotta, parmesan","https://image.freepik.com/free-photo/delicious-italian-pizza-quattro-formaggi-with-four-cheeses_194143-1059.jpg"
			);

			String pName1 = pizza1.getPizzaName();
			String pName2 = pizza2.getPizzaName();
			String pName3 = pizza3.getPizzaName();
			String pName4 = pizza4.getPizzaName();

			if(pizzaRepository.findPizzaByPizzaName(pName1) != null) {
				System.out.println("Pizza already exists");
			} else {
				pizzaRepository.insert(pizza1);
				System.out.println("Pizza " + pName1 + " has been created.");
			}

			if(pizzaRepository.findPizzaByPizzaName(pName2) != null) {
				System.out.println("Pizza already exists");
			} else {
				pizzaRepository.insert(pizza2);
				System.out.println("Pizza " + pName2 + " has been created.");
			}

			if(pizzaRepository.findPizzaByPizzaName(pName3) != null) {
				System.out.println("Pizza already exists");
			} else {
				pizzaRepository.insert(pizza3);
				System.out.println("Pizza " + pName3 + " has been created.");
			}

			if(pizzaRepository.findPizzaByPizzaName(pName4) != null) {
				System.out.println("Pizza already exists");
			} else {
				pizzaRepository.insert(pizza4);
				System.out.println("Pizza " + pName4 + " has been created.");
			}



		};
	}

}
