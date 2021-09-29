package com.channelpizza.webapp.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.channelpizza.webapp.payload.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.channelpizza.webapp.models.ERole;
import com.channelpizza.webapp.models.Role;
import com.channelpizza.webapp.models.User;
import com.channelpizza.webapp.payload.request.LoginRequest;
import com.channelpizza.webapp.payload.response.JwtResponse;
import com.channelpizza.webapp.payload.response.MessageResponse;
import com.channelpizza.webapp.repository.RoleRepository;
import com.channelpizza.webapp.repository.UserRepository;
import com.channelpizza.webapp.security.jwt.JwtUtils;
import com.channelpizza.webapp.security.services.UserDetailsImpl;

/**
 * Controller class for authentication and registration purposes
 * @author Melih Yayli
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * Authentication for login
	 * @param loginRequest Username and password
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(),
												 userDetails.getUsername(),
												 userDetails.getEmail(),
												 userDetails.getFirstName(),
												 userDetails.getLastName(),
												 userDetails.getPostCode(),
												 roles));
	}

	/**
	 * If no user has been created, an admin account is created. Following accounts will have user role assigned.
	 * @param signUpRequest User credentials consisting of name, address, email etc.
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				signUpRequest.getFirstName(),
				signUpRequest.getLastName(),
				signUpRequest.getPostCode(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		List<Role> checkRoles = roleRepository.findAll(); // get all roles from db

		if(checkRoles.isEmpty()) { // if no role is returned
			Role adminRole = new Role(ERole.ROLE_ADMIN); // create roles
			Role modRole = new Role(ERole.ROLE_MODERATOR);
			Role userRole = new Role(ERole.ROLE_USER);
			roleRepository.save(adminRole);
			roleRepository.save(modRole);
			roleRepository.save(userRole);

			roles.add(adminRole); // assign admin role for the to-be-created account
			user.setRoles(roles);
			userRepository.save(user);

			return ResponseEntity.ok(new MessageResponse("Admin registered successfully!"));


		} else {
			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
						case "admin":
							Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(adminRole);

							break;
						case "mod":
							Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(modRole);

							break;
						default:
							Role userRole = roleRepository.findByName(ERole.ROLE_USER)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(userRole);
					}
				});
			}
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}