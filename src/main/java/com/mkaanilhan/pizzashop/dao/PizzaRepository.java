package com.mkaanilhan.pizzashop.dao;

import com.mkaanilhan.pizzashop.entity.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends MongoRepository<Pizza, String> {
}
