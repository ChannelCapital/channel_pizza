package com.channelpizza.webapp.repository;

import com.channelpizza.webapp.models.Pizza;
import com.channelpizza.webapp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PizzaRepository extends MongoRepository<Pizza, String> {

  Optional<Pizza> findByPizzaName(String pizzaName);

  Boolean existsByPizzaName(String pizzaName);

  Optional<Pizza> findPizzasByPizzaIngredientsContains(String pizzaIngredient);

}
