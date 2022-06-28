package edu.sabanciuniv.project.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.sabanciuniv.project.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

	List<Product> findByNameContaining(String name);
	List<Product> findByPublished(boolean published);
	

}

