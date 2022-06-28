package edu.sabanciuniv.project.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.sabanciuniv.project.model.ERole;
import edu.sabanciuniv.project.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	
	Optional<Role> findByName(ERole name);
}
