package com.mkaanilhan.pizzashop;

import com.mkaanilhan.pizzashop.dao.PizzaRepository;
import com.mkaanilhan.pizzashop.dao.OrderRepository;
import com.mkaanilhan.pizzashop.dao.UserRepository;
import com.mkaanilhan.pizzashop.entity.Pizza;
import com.mkaanilhan.pizzashop.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    public PizzaRepository pizzaRepository;
    public UserRepository userRepository;
    public OrderRepository orderRepository;

    public DbSeeder(PizzaRepository pizzaRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.pizzaRepository = pizzaRepository;
        this.userRepository = userRepository;
        this.orderRepository =  orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Pizza pizza1 =  new Pizza("https://i.imgur.com/cuuINKd.jpg",
                "Kebab Pizza",
                8.00,
                "Grill chicken chunks. red onions, jalapeno");

        Pizza pizza2 =  new Pizza("https://i.imgur.com/1Mx7wvQ.jpg",
                "New York Pizza",
                3.00,
                "Double pepperoni and mix peppers");

        Pizza pizza3 =  new Pizza("https://i.imgur.com/JWI3ylK.jpg",
                "Chicago Pizza",
                4.00,
                "Mince meat, achar, mix peppers and sweetcorn");

        Pizza pizza4 =  new Pizza("https://i.imgur.com/lEtBhSq.jpg",
                "Neapolitan Pizza",
                5.00,
                "Cheese & Tomato" );

        Pizza pizza5 =  new Pizza("https://i.imgur.com/SxViCb3.jpg",
                "Veggie Pizza",
                2.00,
                "Mushrooms, green peppers, sweetcorn, red onion");

        Pizza pizza6 =  new Pizza("https://i.imgur.com/M7LEQkU.jpg",
                "Mexican Pizza",
                5.00,
                "Mince meat, red onion. mix pepper, jalapeno");


        this.userRepository.deleteAll();
        this.pizzaRepository.deleteAll();
        this.orderRepository.deleteAll();

        List<Pizza> pizzas = Arrays.asList(pizza1,pizza2,pizza3,pizza4,pizza5, pizza6);

        this.pizzaRepository.saveAll(pizzas);

    }
}


