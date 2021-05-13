package com.mkaanilhan.pizzashop.service.mongo;

import com.mkaanilhan.pizzashop.dao.OrderRepository;
import com.mkaanilhan.pizzashop.dao.UserRepository;
import com.mkaanilhan.pizzashop.entity.Pizza;
import com.mkaanilhan.pizzashop.entity.pizzaListResponse;
import com.mkaanilhan.pizzashop.entity.Order;
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

    @Override
    public void addOrder(pizzaListResponse pizzaListResponse) {

        Optional<User> user = userRepository.findById(pizzaListResponse.getUserID());

        double totalPrice = 0;

        for (Pizza pizza : pizzaListResponse.getPizzaID()){

           totalPrice += pizza.getPrice();

        }

        Order order = new Order(user.get().getId(),
                pizzaListResponse.getPizzaID(),
                new Date(),totalPrice);

        orderRepository.insert(order);

        user.get().getOrders().add(order);

        user.get().setBasket(new ArrayList<>());

        userRepository.save(user.get());

    }

    @Override
    public List<Order> getOrderById(String userID) {

        List<Order> getAllOrder = orderRepository.findAll();

        List<Order> orderList = new ArrayList<>();

        for (int i = 0; i < getAllOrder.size(); i++) {

            if (getAllOrder.get(i).getUserID().equals(userID)) {

                orderList.add(getAllOrder.get(i));
            }

        }

        return orderList;

    }

    @Override
    public List<Order> getAllOrder() {

        return orderRepository.findAll();
    }


}
