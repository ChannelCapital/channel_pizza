package com.mkaanilhan.pizzashop.controller;


import com.mkaanilhan.pizzashop.entity.*;
import com.mkaanilhan.pizzashop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //login
    @PostMapping("/signin")
    public User signIn(@RequestBody UserLogin login) {

        return userService.signIn(login);

    }

    //all
    @GetMapping("/alluser")
    public List<User> getAllUser() {

        return userService.getAllUser();

    }

    //add
    @PostMapping("/adduser")
    public void addUser(@RequestBody User user) {

        user.setFavorites(new ArrayList<>());
        user.setOrders(new ArrayList<>());
        user.setBasket(new ArrayList<>());

        userService.addUser(user);

    }

    //adduserfavorites
    @PostMapping("/addfavorite")
    public void addFavorite(@RequestBody PizzaResponse pizzaResponse) {

        userService.addFavorite(pizzaResponse);

    }

    //getfavoritesbyuser
    @GetMapping("/getfavoritebyuser/{userID}")
    public List<Pizza> getFavoriteByUser(@PathVariable String userID) {

        return userService.getFavoriteByUser(userID);

    }

    //addbasketfavorites
    @PostMapping("/addbasket")
    public void addBasket(@RequestBody PizzaResponse pizzaResponse) {

        userService.addBasket(pizzaResponse);

    }

    @GetMapping("/getbasketbyuser/{userID}")
    public List<Pizza> getBasketByUser(@PathVariable String userID) {

        return userService.getBasketByUser(userID);

    }

    //update
    @PostMapping("/updateuser")
    public void updateUser(@RequestBody User user) {

        userService.updateUser(user);

    }

    //removeitem
    @PostMapping("/removebasketitem")
    public void removeBasketItem(@RequestBody PizzaResponse pizzaResponse){

        userService.removeBasketItem(pizzaResponse);

    }

    //removefromfav
    @PostMapping("/removefavoriteitem")
    public void removeFavoriteItem(@RequestBody PizzaResponse pizzaResponse){

        userService.removeFavoriteItem(pizzaResponse);

    }

}
