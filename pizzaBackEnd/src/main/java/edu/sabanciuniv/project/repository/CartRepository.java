package edu.sabanciuniv.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.sabanciuniv.project.model.Cart;
import edu.sabanciuniv.project.model.User;

public interface CartRepository extends MongoRepository<Cart, String> {

	List<Cart> deleteByUser(User user);
	List<Cart> findAllByUser(User user);
}
