package edu.sabanciuniv.project.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.sabanciuniv.project.model.Address;


public interface AddressRepository extends MongoRepository<Address,String> {
	
	Optional<Address> findByUserId(String userId);
}
