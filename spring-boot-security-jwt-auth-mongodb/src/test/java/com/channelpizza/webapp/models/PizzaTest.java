package com.channelpizza.webapp.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PizzaTest {

    Pizza pizza = new Pizza(
            "Margarita", 2.5, "Tomato sauce, Cheese", "http://image.url/file.jpg", 1
    );

    @Test
    void getPizzaName() {
        assertEquals("Margarita", pizza.getPizzaName());
    }

    @Test
    void getPizzaPrice() {
        assertEquals(2.5, pizza.getPizzaPrice());
    }

    @Test
    void getPizzaIngredients() {
        assertEquals("Tomato sauce, Cheese", pizza.getPizzaIngredients());
    }

    @Test
    void getPizzaImageUrl() {
        assertEquals("http://image.url/file.jpg", pizza.getPizzaImageUrl());
    }

    @Test
    void getQuantity() {
        assertEquals(1, pizza.getQuantity());
    }
}