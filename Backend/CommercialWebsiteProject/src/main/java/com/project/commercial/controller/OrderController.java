package com.project.commercial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.commercial.model.Basket;
import com.project.commercial.model.Order;
import com.project.commercial.repository.BasketRepository;
import com.project.commercial.repository.OrderRepository;
import com.project.commercial.service.BasketService;
import com.project.commercial.service.OrderService;

/**
 * This controller is for taking request to create new orders by connecting with order service. 
 * 8081 port is for frontend vue application port and we allowed this port
 * in here.  
 * @author fatih akguc
 * @return ok, 200 response when order is created.
 */

@RestController
@RequestMapping("")
@CrossOrigin("http://localhost:8081")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BasketController basketController;
	
	@Autowired
	private BasketRepository basketRepository;
	private BasketService basketService;
	
	@Autowired
	private SequenceGeneratorService service;
	
	public OrderController(OrderRepository orderRepository, OrderService orderService) {
		super();
		this.orderRepository = orderRepository;
		this.orderService = orderService;
	}
	
	@GetMapping("/orders")
	public List<Order> findAllOrders(){
		return orderRepository.findAll();
	}
	
	@PostMapping("/createOrder/{totalCost}")
	public ResponseEntity<Order> createOrder(@PathVariable("totalCost") double totalCost) {
		List<Basket> basket = basketController.findAllOrders();
		Order newOrder = new Order(totalCost, basket);
		newOrder.setId(service.generateSequence(Order.SEQUENCE_NAME));
		return ResponseEntity.ok(orderService.createOrder(newOrder));
	}

	

}
