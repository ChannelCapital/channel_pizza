package com.channelpizza.webapp.controllers;

import com.channelpizza.webapp.models.Order;
import com.channelpizza.webapp.repository.OrderRepository;
import com.channelpizza.webapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	RoleRepository roleRepository;

	@GetMapping("")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@GetMapping("{userId}")
	@PreAuthorize("hasRole('USER')")
	public List<Order> getOrdersByUserId(@PathVariable ("userId") String userId) {
		return orderRepository.findOrdersByUserId(userId);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Object> createOrder(@Valid @RequestBody Order order) {
		Order savedOrder = orderRepository.save(order);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedOrder.getId()).toUri();

		System.out.println("Order has been created.");
		return ResponseEntity.created(location).build();
	}

}
