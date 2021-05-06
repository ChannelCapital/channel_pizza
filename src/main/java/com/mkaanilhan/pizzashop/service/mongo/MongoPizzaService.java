package com.mkaanilhan.pizzashop.service.mongo;

import com.mkaanilhan.pizzashop.dao.PizzaRepository;
import com.mkaanilhan.pizzashop.entity.Pizza;
import com.mkaanilhan.pizzashop.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoPizzaService implements PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Override
    public List<Pizza> getAllPizza() {
        return pizzaRepository.findAll();
    }
}
