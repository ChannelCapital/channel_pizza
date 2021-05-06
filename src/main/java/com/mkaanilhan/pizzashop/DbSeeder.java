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

        Pizza pizza1 =  new Pizza("Kebab Pizza", 7.99, null);
        Pizza pizza2 =  new Pizza("New York Pizza", 3.99, null);
        Pizza pizza3 =  new Pizza("Chicago Pizza", 4.99, null);
        Pizza pizza4 =  new Pizza("Neapolitan Pizza", 5.99, null );
        Pizza pizza5 =  new Pizza("Veggie Pizza", 2.99, null);


        User user1 = new User("kaan@mail.com", "123", "Kozyatagi", new ArrayList<>(), new ArrayList<>());

        this.userRepository.deleteAll();
        this.pizzaRepository.deleteAll();
        this.orderRepository.deleteAll();

        List<Pizza> pizzas = Arrays.asList(pizza1,pizza2,pizza3,pizza4,pizza5);
        List<User> users = Arrays.asList(user1);


        this.userRepository.saveAll(users);
        this.pizzaRepository.saveAll(pizzas);

    }
}


