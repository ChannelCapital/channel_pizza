package com.project.commercial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.commercial.model.Order;
import com.project.commercial.repository.OrderRepository;

/**
 * Makes connection with the order repository. Business issues are created in here
 * @author fatih akguc
 */

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@Autowired
	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
		System.out.println("Orderservice worked ");
	}

	public Order findOrderById(long id) {
		return orderRepository.findProductById(id);
	}

	public Order createOrder(Order newProduct) {
		return orderRepository.save(newProduct);
	}

	


	


}
