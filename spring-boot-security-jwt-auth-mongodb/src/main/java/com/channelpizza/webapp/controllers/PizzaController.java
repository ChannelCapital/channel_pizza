package com.channelpizza.webapp.controllers;

import com.channelpizza.webapp.models.Pizza;
import com.channelpizza.webapp.repository.PizzaRepository;
import com.channelpizza.webapp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/pizzas")
public class PizzaController {

	@Autowired
	PizzaRepository pizzaRepository;

	@Autowired
	RoleRepository roleRepository;

	@GetMapping("")
	public List<Pizza> getAllPizza() {
		return pizzaRepository.findAll();
	}

	@GetMapping("{pizzaId}")
	public Optional<Pizza> getAllPizza(@PathVariable ("pizzaId") String pizzaId) {
		return pizzaRepository.findById(pizzaId);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> createPizza(@Valid @RequestBody Pizza pizza) {
		Pizza savedPizza = pizzaRepository.save(pizza);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPizza.getId()).toUri();

		System.out.println("Pizza has been created.");
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("{pizzaId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deletePizza(@PathVariable ("pizzaId") String pizzaId) {
		Optional<Pizza> pizza = pizzaRepository.findById(pizzaId);

		if (pizza.isPresent()) {
			System.out.println("Pizza with id " + pizzaId + " has been deleted.");
			pizzaRepository.deleteById(pizzaId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			System.out.println("Pizza with id " + pizzaId + " not found!");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/{pizzaId}")
	@PreAuthorize("hasRole('ADMIN')")
	Pizza updatePizza(@RequestBody Pizza newPizza, @PathVariable String pizzaId) {
		return pizzaRepository.findById(pizzaId).map(pizza -> {
			if(newPizza.getPizzaName() != null) {
				pizza.setPizzaName(newPizza.getPizzaName());
			}
			if(newPizza.getPizzaIngredients() != null) {
				pizza.setPizzaIngredients(newPizza.getPizzaIngredients());
			}
			if(newPizza.getPizzaPrice() != 0.0) {
				pizza.setPizzaPrice(newPizza.getPizzaPrice());
			}
			if(newPizza.getPizzaImageUrl() != null) {
				pizza.setPizzaImageUrl(newPizza.getPizzaImageUrl());
			}
			return pizzaRepository.save(pizza);
		})
				.orElseGet(() -> {
					System.out.println("An error occured while updating the pizza.");
					return null;
				});
	}
}
