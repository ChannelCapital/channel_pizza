package com.mkaanilhan.pizzashop.controller;

import com.mkaanilhan.pizzashop.entity.Pizza;
import com.mkaanilhan.pizzashop.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @GetMapping("/all")
    public List<Pizza> getAllPizza() {

        return pizzaService.getAllPizza();

    }

}
