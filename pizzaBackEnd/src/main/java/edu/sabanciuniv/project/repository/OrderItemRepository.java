package edu.sabanciuniv.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.sabanciuniv.project.model.OrderItem;


public interface OrderItemRepository extends MongoRepository<OrderItem, String> {

}
