package com.project.commercial.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.project.commercial.model.Basket;

/**
 * This repository connects to the database and execute the methods below related 
 * to the basket objects.
 * @author fatih akguc
 */

public interface BasketRepository extends MongoRepository<Basket, Integer> {
	List<Basket> findAll();
	//List<Basket> findById(int id); Can be addded if needed
	Basket findProductById(long id);
	Basket save(Basket order);
	void delete(Basket order);
	
}