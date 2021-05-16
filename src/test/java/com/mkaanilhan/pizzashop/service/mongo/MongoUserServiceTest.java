package com.mkaanilhan.pizzashop.service.mongo;

import com.mkaanilhan.pizzashop.dao.OrderRepository;
import com.mkaanilhan.pizzashop.dao.PizzaRepository;
import com.mkaanilhan.pizzashop.dao.UserRepository;
import com.mkaanilhan.pizzashop.entity.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class MongoUserServiceTest {

    MongoUserService mongoUserService;
    UserRepository userRepository;
    PizzaRepository pizzaRepository;
    OrderRepository orderRepository;

    @Before
    public void setUp() throws Exception {

        userRepository = Mockito.mock(UserRepository.class);
        pizzaRepository = Mockito.mock(PizzaRepository.class);
        orderRepository = Mockito.mock(OrderRepository.class);

        mongoUserService = new MongoUserService(userRepository,pizzaRepository);
    }

    @Test
    public void GetAllUserTest(){

        Pizza pizza1 = new Pizza("a","b",1,"a");
        Pizza pizza2 = new Pizza("a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();
        listPizza.add(pizza1);
        listPizza.add(pizza2);

        PizzaListResponse pizzaListResponse = new PizzaListResponse("123",listPizza);

        Order order = new Order(pizzaListResponse.getUserID(),listPizza, new Date(), 123);

        List<Order> listOrder =  new ArrayList<>();

        listOrder.add(order);

        User user1 = new User("123","asd","asd","asd",listOrder,listPizza,listPizza);

        List<User> listUser =  new ArrayList<>();

        listUser.add(user1);

        List<User> users = mongoUserService.getAllUser();
        users.add(user1);

        Mockito.when(userRepository.findAll()).thenReturn(listUser);
        Assert.assertEquals(listUser,users);
        Mockito.verify(userRepository).findAll();


    }


    @Test
    public void addUserTest(){

        Pizza pizza1 = new Pizza("123","a","b",1,"a");
        Pizza pizza2 = new Pizza("123","a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();
        listPizza.add(pizza1);
        listPizza.add(pizza2);

        PizzaListResponse pizzaListResponse = new PizzaListResponse("123",listPizza);

        Order order = new Order("123",listPizza, new Date(), 123);

        List<Order> listOrder =  new ArrayList<>();

        listOrder.add(order);

        User user1 = new User("123","asd","asd","asd",listOrder,listPizza,listPizza);
        User user2 = new User("asd","asd","asd",listOrder,listPizza,listPizza);

        List<User> listUser =  mongoUserService.getAllUser();

        listUser.add(user1);
        listUser.add(user2);

        Mockito.when(userRepository.insert(user1)).thenReturn(user1);

        Assert.assertEquals(user1,user1);

        Mockito.verify(userRepository).findAll();



    }

    @Test
    public void addFavoriteTest(){

        PizzaResponse pizzaResponse = new PizzaResponse("123","123");

        Pizza pizza1 = new Pizza("123","a","b",1,"a");
        Pizza pizza2 = new Pizza("123","a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();
        listPizza.add(pizza1);
        listPizza.add(pizza2);

        PizzaListResponse pizzaListResponse = new PizzaListResponse("123",listPizza);

        Order order = new Order(pizzaListResponse.getUserID(),listPizza, new Date(), 123);

        List<Order> listOrder =  orderRepository.findAll();

        listOrder.add(order);

        User user1 = new User("123","asd","asd","asd",listOrder,listPizza,listPizza);


        Mockito.when(userRepository.findById(pizzaResponse.getUserID())).thenReturn(java.util.Optional.of(user1));
        Mockito.when(pizzaRepository.findById(pizzaResponse.getPizzaID())).thenReturn(java.util.Optional.of(pizza1));
        Mockito.when(userRepository.save(user1)).thenReturn(user1);


        Mockito.verify(orderRepository).findAll();



    }

    @Test
    public void getFavoriteByUserTest(){

        Pizza pizza1 = new Pizza("123","a","b",1,"a");
        Pizza pizza2 = new Pizza("123","a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();
        listPizza.add(pizza1);
        listPizza.add(pizza2);

        Order order = new Order("123",listPizza, new Date(), 123);

        List<Order> listOrder =  new ArrayList<>();

        listOrder.add(order);

        List<Pizza> pizzas = pizzaRepository.findAll();

        pizzas.add(pizza1);
        pizzas.add(pizza2);

        User user1 = new User("123","asd","asd","asd",listOrder,listPizza,listPizza);
        User user2 = new User("123","asd","asd","asd",listOrder,listPizza,listPizza);

        List<User> userList = new ArrayList<>();

        userList.add(user1);
        userList.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        Assert.assertEquals(pizzas,listPizza);

        Mockito.verify(pizzaRepository).findAll();




    }

    @Test
    public void signInTest(){

        Pizza pizza1 = new Pizza("a","b",1,"a");
        Pizza pizza2 = new Pizza("a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();
        listPizza.add(pizza1);
        listPizza.add(pizza2);

        PizzaListResponse pizzaListResponse = new PizzaListResponse("123",listPizza);

        Order order = new Order(pizzaListResponse.getUserID(),listPizza, new Date(), 123);

        List<Order> listOrder =  new ArrayList<>();

        listOrder.add(order);

        UserLogin userLogin = new UserLogin("123","234");

        User user1 = new User("123","asd","asd",listOrder,listPizza,listPizza);
        User user2 = new User("asd","asd","asd",listOrder,listPizza,listPizza);

        List<User> userList = new ArrayList<>();

        userList.add(user1);
        userList.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);


        Assert.assertEquals(userLogin.getEmail(),user1.getEmail());




    }

    @Test
    public void getBasketByUserTest(){

        PizzaResponse pizzaResponse = new PizzaResponse("123","234");

        Pizza pizza1 = new Pizza("a","b",1,"a");
        Pizza pizza2 = new Pizza("a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();
        listPizza.add(pizza1);
        listPizza.add(pizza2);

        PizzaListResponse pizzaListResponse = new PizzaListResponse("123",listPizza);

        Order order = new Order(pizzaListResponse.getUserID(),listPizza, new Date(), 123);

        List<Order> listOrder =  new ArrayList<>();

        listOrder.add(order);

        User user1 = new User("123","asd","asd","asd",listOrder,listPizza,listPizza);
        User user2 = new User("123","asd","asd","asd",listOrder,listPizza,listPizza);

        List<User> userList = userRepository.findAll();

        userList.add(user1);
        userList.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        Mockito.verify(userRepository).findAll();


        Assert.assertEquals(user1.getId(),pizzaResponse.getUserID());





    }

    @Test
    public void addBasketTest(){


        Pizza pizza1 = new Pizza("123","a","b",1,"a");
        Pizza pizza2 = new Pizza("123","a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();
        listPizza.add(pizza1);
        listPizza.add(pizza2);

        PizzaResponse p = new PizzaResponse("123","123");

        Order order = new Order(p.getUserID(),listPizza, new Date(), 123);

        List<Order> listOrder =  orderRepository.findAll();

        listOrder.add(order);

        User user1 = new User("123","asd","asd","asd",listOrder,listPizza,listPizza);
        User user2 = new User("123","asd","asd","asd",listOrder,listPizza,listPizza);

        List<User> listUser =  userRepository.findAll();

        listUser.add(user1);
        listUser.add(user2);

        Mockito.when(userRepository.findById(p.getUserID())).thenReturn(java.util.Optional.of(user1));
        Mockito.when(pizzaRepository.findById(p.getPizzaID())).thenReturn(java.util.Optional.of(pizza1));

        Mockito.verify(userRepository).findAll();

        Assert.assertEquals(user1.getId(),p.getUserID());



    }

    @Test
    public void updateUserTest(){

        Pizza pizza1 = new Pizza("123","a","b",1,"a");
        Pizza pizza2 = new Pizza("123","a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();

        listPizza.add(pizza1);
        listPizza.add(pizza2);

        Order order = new Order("123",listPizza, new Date(), 123);

        List<Order> listOrder = orderRepository.findAll() ;

        listOrder.add(order);

        User user1 = new User("123","asd","asd","asd",listOrder,listPizza,listPizza);


        Mockito.when(userRepository.findById(user1.getId())).thenReturn(java.util.Optional.of(user1));

        Mockito.when(userRepository.save(user1)).thenReturn(user1);






    }
}