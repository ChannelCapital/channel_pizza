package com.example.pizzaDeliveryApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.pizzaDeliveryApp.model.PizzaUser;

@Repository
public interface PizzaUserRepository extends MongoRepository<PizzaUser, String> {

}
