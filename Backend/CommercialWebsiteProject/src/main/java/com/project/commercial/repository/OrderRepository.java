package com.project.commercial.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.commercial.model.Order;

/**
 * This repository connects to the database and execute the methods below related 
 * to the order objects.
 * @author fatih akguc
 */

public interface OrderRepository extends MongoRepository<Order, Integer> {
	List<Order> findAll();
	List<Order> findById(int id);
	Order findProductById(long id);
	Order save(Order order);
	void delete(Order order);
	
}