package com.channelpizza.webapp.repository;

import com.channelpizza.webapp.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Item entity
 * @author Melih Yayli
 */
public interface OrderRepository extends MongoRepository<Order, String> {

  Optional<Order> findOrderById (String orderId);

  Boolean existsOrderById(String orderId);

  List<Order> findOrdersByUserId(String orderByUserId);

}
