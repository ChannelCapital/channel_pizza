package com.mkaanilhan.pizzashop.dao;

import com.mkaanilhan.pizzashop.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
