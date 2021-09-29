package com.channelpizza.webapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.channelpizza.webapp.models.User;

/**
 * Repository for item User entity
 * @author Melih Yayli
 */
public interface UserRepository extends MongoRepository<User, String> {

  //methods

  /**
   * <p>The method returns a User entity.</p>
   * @param username
   * @return User
   */
  Optional<User> findByUsername(String username);


  /**
   * <p>The method is used to check if the username is already in use or not.</p>
   * @param username
   * @return shows if user username is already in database or not
   */
  Boolean existsByUsername(String username);

  /**
   * <p>The method is used to check if the email is already in use or not.</p>
   * @param email
   * @return shows if user email is already in database or not
   */
  Boolean existsByEmail(String email);

}
