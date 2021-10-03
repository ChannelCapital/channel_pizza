package com.example.pizzaDeliveryApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.pizzaDeliveryApp.model.PizzaSuggestion;

@Repository
public interface PizzaSuggestionRepository extends MongoRepository<PizzaSuggestion, String> {

}
