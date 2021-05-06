package com.mkaanilhan.pizzashop.dao;

import com.mkaanilhan.pizzashop.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {

}
