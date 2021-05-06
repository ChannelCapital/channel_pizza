package com.mkaanilhan.pizzashop.service.mongo;

import com.mkaanilhan.pizzashop.dao.OrderRepository;
import com.mkaanilhan.pizzashop.dao.PizzaRepository;
import com.mkaanilhan.pizzashop.dao.UserRepository;
import com.mkaanilhan.pizzashop.entity.ApiResponse;
import com.mkaanilhan.pizzashop.entity.Order;
import com.mkaanilhan.pizzashop.entity.Pizza;
import com.mkaanilhan.pizzashop.entity.User;
import com.mkaanilhan.pizzashop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MongoOrderService implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PizzaRepository pizzaRepository;

    @Override
    public void addOrder(ApiResponse apiResponse) {

        Optional<User> user = userRepository.findById(apiResponse.getUserID());
        Optional<Pizza> pizza = pizzaRepository.findById(apiResponse.getPizzaID());

        Order order = new Order(user.get().getId(), pizza.get(), new Date());

        orderRepository.insert(order);

        user.get().getOrders().add(order);

        userRepository.save(user.get());


    }

    @Override
    public List<Order> getUserOrderById(String userID) {

        List<Order> orders = orderRepository.findAll();

        List<Order> orderList = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {

            if (orders.get(i).getUser().equals(userID)) {

                orderList.add(orders.get(i));
            }

        }

        return orderList;

    }

    @Override
    public List<Order> getOrders() {

        return orderRepository.findAll();
    }


}
