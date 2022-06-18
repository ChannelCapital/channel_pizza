package com.project.commercial.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.commercial.model.User;

/**
 * This repository connects to the database and execute the methods below related 
 * to the user objects.
 * @author fatih akguc
 */

public interface UserRepository extends MongoRepository<User, Integer> {
	List<User> findAll();
	List<User> findByEmail(String email);
	User findUserByEmail(String email);
	User findUserById(long id);
	User save(User user);
	void delete(User user);
	
}