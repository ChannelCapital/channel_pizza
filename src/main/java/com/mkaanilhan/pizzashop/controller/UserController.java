package com.mkaanilhan.pizzashop.controller;


import com.mkaanilhan.pizzashop.entity.ApiResponse;
import com.mkaanilhan.pizzashop.entity.Pizza;
import com.mkaanilhan.pizzashop.entity.User;
import com.mkaanilhan.pizzashop.entity.UserLogin;
import com.mkaanilhan.pizzashop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public User login(@RequestBody UserLogin login){

        return userService.login(login);

    }

    @GetMapping("/all")
    public List<User> getUsers() {

        return userService.getUsers();

    }

    @PostMapping("/add")
    public void addUser(@RequestBody User user) {

        user.setFavorites(new ArrayList<>());
        user.setOrders(new ArrayList<>());

        userService.addUser(user);

    }

    @PostMapping("/adduserfavorites")
    public void addFavorites(@RequestBody ApiResponse apiResponse) {

        userService.addFavorites(apiResponse);

    }

    @GetMapping("/getfavoritesbyuser/{userID}")
    public List<Pizza> getFavoritesByUser(@PathVariable String userID){

        return userService.getFavoritesByUser(userID);

    }

}
