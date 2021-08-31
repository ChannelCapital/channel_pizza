package com.channelpizza.webapp.repository;

import com.channelpizza.webapp.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

  Optional<Order> findOrderById (String orderId);

  Boolean existsOrderById(String orderId);

  Optional<Order> findOrdersByUserId(String orderByUserId);

}
