package com.mkaanilhan.pizzashop.service.mongo;

import com.mkaanilhan.pizzashop.dao.PizzaRepository;
import com.mkaanilhan.pizzashop.entity.Pizza;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


public class MongoPizzaServiceTest {

    MongoPizzaService mongoPizzaService;
    PizzaRepository pizzaRepository;

    @Before
    public void setUp() {

        pizzaRepository = Mockito.mock(PizzaRepository.class);
        mongoPizzaService = new MongoPizzaService(pizzaRepository);

    }

    @Test
    public void getAllPizzaTest() {

        Pizza pizza1 = new Pizza("123","a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();
        listPizza.add(pizza1);

        List<Pizza> pizzas = mongoPizzaService.getAllPizza();

        pizzas.add(pizza1);

        Mockito.when(pizzaRepository.findAll()).thenReturn(listPizza);
        Assert.assertEquals(listPizza,pizzas);
        Mockito.verify(pizzaRepository).findAll();

    }
}