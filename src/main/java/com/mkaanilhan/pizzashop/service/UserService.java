package com.mkaanilhan.pizzashop.service;

import com.mkaanilhan.pizzashop.entity.ApiResponse;
import com.mkaanilhan.pizzashop.entity.Pizza;
import com.mkaanilhan.pizzashop.entity.User;
import com.mkaanilhan.pizzashop.entity.UserLogin;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    void addUser(User user);
    void addFavorites(ApiResponse apiResponse);
    List<Pizza> getFavoritesByUser(String userID);
    User login(UserLogin login);

}
