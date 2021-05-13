package com.mkaanilhan.pizzashop.service;

import com.mkaanilhan.pizzashop.entity.*;

import java.util.List;

public interface UserService {

    User signIn(UserLogin login);
    List<User> getAllUser();
    List<Pizza> getFavoriteByUser(String userID);
    List<Pizza> getBasketByUser(String userID);
    void addUser(User user);
    void addFavorite(PizzaResponse pizzaResponse);
    void addBasket(PizzaResponse pizzaResponse);
    void updateUser(User user);
    void removeBasketItem(PizzaResponse pizzaResponse);
    void removeFavoriteItem(PizzaResponse pizzaResponse);

}
