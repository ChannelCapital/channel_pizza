package com.example.pizzaDeliveryApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.pizzaDeliveryApp.model.PizzaOrder;

@Repository
public interface PizzaOrderRepository extends MongoRepository<PizzaOrder, String> {

}
