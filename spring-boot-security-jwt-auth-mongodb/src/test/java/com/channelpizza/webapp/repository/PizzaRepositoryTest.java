package com.channelpizza.webapp.repository;

import com.channelpizza.webapp.models.Pizza;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;

class PizzaRepositoryTest {

    @Autowired
    private PizzaRepository underTest;

    @Test
    void itShouldFindByPizzaName() {

        //given
        Pizza pizza = new Pizza(
                "PName",
                2.5,
                "Water, Salt",
                "https://image.url.com/image.jpg",
                1
        );
        underTest.save(pizza);

        //when
        boolean pizzaFoundByName = false;
        if("PName" == pizza.getPizzaName()) {
            pizzaFoundByName = true;
        } else {
            pizzaFoundByName = false;
        }

        //then

    }

//    @Test
//    void itShouldCheckIfPizzaExistsByPizzaName() {
//    }
//
//    @Test
//    void findPizzasByPizzaIngredientsContains() {
//    }
}