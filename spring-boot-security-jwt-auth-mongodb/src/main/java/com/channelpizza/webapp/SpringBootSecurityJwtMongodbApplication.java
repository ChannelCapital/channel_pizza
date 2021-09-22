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

import java.util.ArrayList;
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

			List<Pizza> pizzasToBeCreated = new ArrayList<>();

			pizzasToBeCreated.add(new Pizza(
					"Margherita",
					2.5,
					"tomatoes, mozzarella, basil, olive oil",
					"http://simply-delicious-food.com/wp-content/uploads/2020/06/Grilled-Pizza-Margherita-3.jpg"
			));
			pizzasToBeCreated.add(new Pizza(
					"Pepperoni",
					2.75,
					"pepperoni, mozzarela, oregano",
					"https://www.simplyrecipes.com/thmb/HQJVIRwkySb-QiYv6saC9dEjU5A=/960x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__simply_recipes__uploads__2019__09__easy-pepperoni-pizza-lead-4-82c60893fcad4ade906a8a9f59b8da9d.jpg"
			));
			pizzasToBeCreated.add(new Pizza(
					"Calzone",
					3.99,
					"ricotta, mozzarella, salami, parmesan",
					"https://www.happyfoodstube.com/wp-content/uploads/2016/03/calzone-pizza-picture.jpg"
			));
			pizzasToBeCreated.add(new Pizza(
					"Quattro Formaggi",
					5.05,
					"mozzarella, gorgonzola, ricotta, parmesan",
					"https://image.freepik.com/free-photo/delicious-italian-pizza-quattro-formaggi-with-four-cheeses_194143-1059.jpg"
			));
			pizzasToBeCreated.add(new Pizza(
					"Mushroom",
					5.05,
					"Garlic, Mozzarella, Mushrooms, Arugula, Basil",
					"https://www.theroastedroot.net/wp-content/uploads/2013/08/Chicken-Mushroom-Pesto-Pizza.jpg"
			));
			pizzasToBeCreated.add(new Pizza(
					"Hawaiian",
					5.05,
					"ham, pineapples, cheese",
					"https://sparkpeo.hs.llnwd.net/e1/resize/630m620/e2/guid/Hawaiian-Pizza-SparkRecipes-Un-Chained-Contest-Finalist-/788bdd98-898c-4900-ad38-9fa6ad1fe0dd.jpg"
			));
			pizzasToBeCreated.add(new Pizza(
					"Buffalo",
					5.05,
					"shredded chicken, buffalo sauce, blue cheese crumbles, cilantro",
					"https://assets.website-files.com/5f1651e7d193211bb34def1a/5f1f08dcbf513648fb367834_pizza_t20_KoN9gV.jpg"
			));
			pizzasToBeCreated.add(new Pizza(
					"BBQ Chicken",
					5.05,
					"bbq sauce, mozzarella, chicken, red onions, cilantro",
					"https://www.diabetesfoodhub.org/system/thumbs/system/images/recipes/Recid_76_BBQ_Chicken_Pizza_iStock-484653761_02262018_2959761669.jpg"
			));

			for (int i = 0; i < pizzasToBeCreated.size(); i++) {
				String tempPizzaName = pizzasToBeCreated.get(i).getPizzaName();
				if(pizzaRepository.findPizzaByPizzaName(tempPizzaName) != null) {
					System.out.println("Pizza already exists");
				} else {
					pizzaRepository.insert(pizzasToBeCreated.get(i));
					System.out.println("Pizza " + tempPizzaName + " has been created.");
				}
			}
		};
	}
}
