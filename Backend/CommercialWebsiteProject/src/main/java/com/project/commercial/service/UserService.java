package com.project.commercial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.commercial.model.Basket;
import com.project.commercial.model.User;
import com.project.commercial.repository.BasketRepository;
import com.project.commercial.repository.UserRepository;

/**
 * Makes connection with the user repository. Business issues are created in here
 * @author fatih akguc
 */

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		System.out.println("Userservice worked!");
	}

	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findUserById(long id) {
		return userRepository.findUserById(id);
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAllByEmail(String email){
		return userRepository.findByEmail(email);
	}


	public User deleteUser(User user) {
		userRepository.delete(user);
		user.setId(0);
		user.setEmail(null);
		user.setPassword(null);
		return user;
	}

	public User updateUser(long id, String email, String password) {
		User toUpdateUser = findUserById(id);
		toUpdateUser.setEmail(email);
		toUpdateUser.setPassword(password);
		userRepository.save(toUpdateUser);
		return toUpdateUser ;
	}

	



}
