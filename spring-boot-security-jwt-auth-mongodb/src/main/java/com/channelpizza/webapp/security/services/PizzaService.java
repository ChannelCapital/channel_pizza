package com.channelpizza.webapp.security.services;

import com.channelpizza.webapp.models.Pizza;
import com.channelpizza.webapp.repository.PizzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> getPizzaById(String pizzaId) {
        return pizzaRepository.findById(pizzaId);
    }

    public Pizza createPizza(Pizza pizza) {
        return pizzaRepository.insert(pizza);
    }
}
