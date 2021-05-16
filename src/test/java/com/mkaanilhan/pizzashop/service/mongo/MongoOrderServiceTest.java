package com.mkaanilhan.pizzashop.service.mongo;

import com.mkaanilhan.pizzashop.dao.OrderRepository;
import com.mkaanilhan.pizzashop.dao.PizzaRepository;
import com.mkaanilhan.pizzashop.dao.UserRepository;
import com.mkaanilhan.pizzashop.entity.Order;
import com.mkaanilhan.pizzashop.entity.Pizza;
import com.mkaanilhan.pizzashop.entity.PizzaListResponse;
import com.mkaanilhan.pizzashop.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MongoOrderServiceTest {

    MongoOrderService mongoOrderService;

    OrderRepository orderRepository;
    UserRepository userRepository;
    PizzaRepository pizzaRepository;


    @Before
    public void setUp() {

        pizzaRepository = Mockito.mock(PizzaRepository.class);
        orderRepository = Mockito.mock(OrderRepository.class);
        userRepository = Mockito.mock(UserRepository.class);

        mongoOrderService = new MongoOrderService(orderRepository, userRepository);

    }


    @Test
    public void addOrderTest(){

        Pizza pizza1 = new Pizza("123","a","b",1,"a");
        Pizza pizza2 = new Pizza("123","a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();
        listPizza.add(pizza1);
        listPizza.add(pizza2);

        PizzaListResponse pizzaListResponse = new PizzaListResponse("123",listPizza);

        Order order = new Order("123",pizzaListResponse.getUserID(),listPizza, new Date(), 123);

        List<Order> listOrder =  new ArrayList<>();

        listOrder.add(order);

        List<Order> orders = orderRepository.findAll();

        orders.add(order);

        User user = new User("123","x","y","z",listOrder,listPizza,listPizza);

        Mockito.when(userRepository.findById("123")).thenReturn(java.util.Optional.of(user));
        Mockito.when(orderRepository.insert(order)).thenReturn(order);
        Mockito.when(userRepository.save(user)).thenReturn(user);

        Assert.assertEquals(listOrder,orders);

        Mockito.verify(orderRepository).findAll();



    }

    @Test
    public void getOrderByIdTest(){

        Pizza pizza1 = new Pizza("123","a","b",1,"a");
        Pizza pizza2 = new Pizza("123","a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();
        listPizza.add(pizza1);
        listPizza.add(pizza2);

        Order order = new Order("12345","123",listPizza, new Date(), 123);

        List<Order> listOrder =  new ArrayList<>();

        listOrder.add(order);

        List<Order> orders = mongoOrderService.getAllOrder();

        orders.add(order);

        Mockito.when(orderRepository.findAll()).thenReturn(listOrder);
        Assert.assertEquals(listOrder,orders);
        Mockito.verify(orderRepository).findAll();


    }

    @Test
    public void getAllOrderTest(){

        Pizza pizza1 = new Pizza("123","a","b",1,"a");
        Pizza pizza2 = new Pizza("123","a","b",1,"a");

        List<Pizza> listPizza =  new ArrayList<>();
        listPizza.add(pizza1);
        listPizza.add(pizza2);

        Order order = new Order("12345","123",listPizza, new Date(), 123);

        List<Order> listOrder =  new ArrayList<>();

        listOrder.add(order);

        List<Order> orders = mongoOrderService.getAllOrder();

        orders.add(order);

        Mockito.when(orderRepository.findAll()).thenReturn(listOrder);

        Assert.assertEquals(listOrder,orders);
        Mockito.verify(orderRepository).findAll();


    }

}