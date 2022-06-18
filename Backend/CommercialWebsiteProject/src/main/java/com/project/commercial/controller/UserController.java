package com.project.commercial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.commercial.model.User;
import com.project.commercial.repository.UserRepository;
import com.project.commercial.service.UserService;

/**
 * This controller is for showing, creating, searching and deleting user by connecting 
 * user service. 8081 port is for frontend vue application port and we allowed this port
 * in here.  
 * @author fatih akguc
 * @return ok, 200 response when methods are executed.
 */

@RestController
@RequestMapping("")
@CrossOrigin("http://localhost:8081")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SequenceGeneratorService service;
	
	public UserController(UserRepository userRepository, UserService userService) {
		super();
		this.userRepository = userRepository;
		this.userService = userService;
	}


	@GetMapping("/users")
	public ResponseEntity<List<User>> findAllUsers(){
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<User> findUserById(@PathVariable("id") long id) {
			return ResponseEntity.ok(userService.findUserById(id));
		}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<List<User>> findUserByEmail(@PathVariable("email") String email){
		List<User> users = userService.findAllByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(users);
		
	}
	
	@PostMapping("/add/{email}/{password}/{phone}/{address}/{address2}/{region}/{country}/{zip}")
	public ResponseEntity<User> createNewUser(@PathVariable("email") String email, @PathVariable("password") String password,
			@PathVariable("phone") String phone,@PathVariable("address") String address, @PathVariable("address2") String address2, @PathVariable("region") String region,
			@PathVariable("country") String country, @PathVariable("zip") String zip) {
		User newUser = new User(email, password, phone,address,address2,region,country,zip);
		newUser.setId(service.generateSequence(User.SEQUENCE_NAME));
		return ResponseEntity.ok(userService.createUser(newUser));
		
	}
	
	
	@PutMapping("/update/{id}/{email}/{password}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @PathVariable("email") String email, @PathVariable("password") String password) {
		User newUser = new User(id,email, password);
		return ResponseEntity.ok(userService.updateUser(newUser.getId(),newUser.getEmail(),newUser.getPassword()));
		
	}
	
	@PostMapping("/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
		User deleteUser = userService.findUserById(id);
		return ResponseEntity.ok(userService.deleteUser(deleteUser));
		
	}

}
