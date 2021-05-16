package com.mkaanilhan.pizzashop.service.mongo;

import com.mkaanilhan.pizzashop.dao.OrderRepository;
import com.mkaanilhan.pizzashop.dao.UserRepository;
import com.mkaanilhan.pizzashop.entity.Pizza;
import com.mkaanilhan.pizzashop.entity.PizzaListResponse;
import com.mkaanilhan.pizzashop.entity.Order;
import com.mkaanilhan.pizzashop.entity.User;
import com.mkaanilhan.pizzashop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Murat Kaan Ilhan
 * @since 15.05.2021
 */

@Service
public class MongoOrderService implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    public MongoOrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    /**
     *This method is used for adding new order to the db for specific user.
     * @param pizzaListResponse is json object which has userID and pizzaID.
     */

    @Override
    public void addOrder(PizzaListResponse pizzaListResponse) {

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

    /**
     *This method is used for to get all placed orders for specific user.
     * @param userID is used for finding user from databases.
     * @return order list for given userID.
     */

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

    /**
     * This method is used to get all order from databases.
     * @return get all orders from databases.
     */

    @Override
    public List<Order> getAllOrder() {

        return orderRepository.findAll();
    }


}
