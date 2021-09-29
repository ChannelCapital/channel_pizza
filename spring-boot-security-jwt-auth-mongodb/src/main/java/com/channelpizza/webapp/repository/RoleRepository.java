package com.channelpizza.webapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.channelpizza.webapp.models.ERole;
import com.channelpizza.webapp.models.Role;
/**
 * Repository for item Role entity
 * @author Melih Yayli
 */
public interface RoleRepository extends MongoRepository<Role, String> {
  /**
   * <p>The method is used to check if the user role exists or not.</p>
   * @param name
   * @return shows if user role exists or not
   */
  Optional<Role> findByName(ERole name);
}
