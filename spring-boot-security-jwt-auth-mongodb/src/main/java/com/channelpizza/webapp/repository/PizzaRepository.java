package com.channelpizza.webapp.repository;

import com.channelpizza.webapp.models.Pizza;
import com.channelpizza.webapp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
/**
 * Repository for item Pizza entity
 * @author Melih Yayli
 */
public interface PizzaRepository extends MongoRepository<Pizza, String> {

  /**
   * <p>The method is used to filter for pizzas containing a specific ingredient.</p>
   * @param pizzaIngredient
   * @return shows if pizza is already in database or not
   */
  List<Pizza> findPizzasByPizzaIngredientsContaining(String pizzaIngredient);

  /**
   * <p>The method is used to check if the pizza is already in database or not.</p>
   * @param pizzaName
   * @return shows if pizza is already in database or not
   */
  Pizza findPizzaByPizzaName(String pizzaName);


}
