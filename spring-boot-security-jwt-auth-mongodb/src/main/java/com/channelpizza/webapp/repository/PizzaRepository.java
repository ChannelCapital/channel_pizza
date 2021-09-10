package com.channelpizza.webapp.repository;

import com.channelpizza.webapp.models.Pizza;
import com.channelpizza.webapp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends MongoRepository<Pizza, String> {

  List<Pizza> findPizzasByPizzaIngredientsContaining(String pizzaIngredient);

  Pizza findPizzaByPizzaName(String pizzaName);

}
