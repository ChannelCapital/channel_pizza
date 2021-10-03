package com.example.pizzaDeliveryApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.pizzaDeliveryApp.model.Pizza;

@Repository
public interface PizzaRepository extends MongoRepository<Pizza, String> {

}
