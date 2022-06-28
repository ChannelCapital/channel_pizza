package edu.sabanciuniv.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.sabanciuniv.project.model.Order;


public interface OrderRepository extends MongoRepository<Order, String> {

}
