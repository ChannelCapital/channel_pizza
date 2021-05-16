package com.mkaanilhan.pizzashop.service.mongo;

import com.mkaanilhan.pizzashop.dao.PizzaRepository;
import com.mkaanilhan.pizzashop.entity.Pizza;
import com.mkaanilhan.pizzashop.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Murat Kaan Ilhan
 * @since 15.05.2021
 */

@Service
public class MongoPizzaService implements PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    public MongoPizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    /**
     * This method is used for getting all pizza item from database.
     * @return all pizza item with all specs.
     */

    @Override
    public List<Pizza> getAllPizza() {
        return pizzaRepository.findAll();
    }
}
