package com.mkaanilhan.pizzashop.service.mongo;

import com.mkaanilhan.pizzashop.dao.PizzaRepository;
import com.mkaanilhan.pizzashop.dao.UserRepository;
import com.mkaanilhan.pizzashop.entity.ApiResponse;
import com.mkaanilhan.pizzashop.entity.Pizza;
import com.mkaanilhan.pizzashop.entity.User;
import com.mkaanilhan.pizzashop.entity.UserLogin;
import com.mkaanilhan.pizzashop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MongoUserService implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PizzaRepository pizzaRepository;

    @Override
    public List<User> getUsers() {

        return userRepository.findAll();

    }

    @Override
    public void addUser(User user) {

        userRepository.insert(user);

    }

    @Override
    public void addFavorites(ApiResponse apiResponse) {

        Optional<User> user = userRepository.findById(apiResponse.getUserID());
        Optional<Pizza> pizza = pizzaRepository.findById(apiResponse.getPizzaID());

        user.get().getFavorites().add(pizza.get());

        userRepository.save(user.get());

    }

    @Override
    public List<Pizza> getFavoritesByUser(String userID) {

        List<User> userList = userRepository.findAll();
        List<Pizza> pizzaList = null;

        for (int i = 0; i < userList.size(); i++) {

            if (userList.get(i).getId().equals(userID)) {

                pizzaList = userList.get(i).getFavorites();

            }

        }

        return pizzaList;
    }

    @Override
    public User login(UserLogin login) {

        List<User> userList = userRepository.findAll();
        User user = null;

        for (User u : userList) {

            if (login.getEmail().equals(u.getEmail()) && login.getPassword().equals(u.getPassword())) {

                user = u;

            }

        }

        return user;

    }

}
