package com.project.commercial.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.commercial.model.Basket;
import com.project.commercial.repository.BasketRepository;
import com.project.commercial.repository.OrderRepository;

/**
 * Makes connection with the basket repository. Business issues are created in here
 * @author fatih akguc
 */


@Service
public class BasketService {
	
	@Autowired
	private BasketRepository basketRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@Autowired
	public BasketService(BasketRepository basketRepository) {
		super();
		this.basketRepository = basketRepository;
		System.out.println("Orderservice worked ");
	}

	public Basket findOrderById(long id) {
		return basketRepository.findProductById(id);
	}

	public Basket updateOrder(int id, int quantity) {
		Basket toUpdateOrder = findOrderById(id);
		toUpdateOrder.setQuantity(quantity);
		basketRepository.save(toUpdateOrder);
		return toUpdateOrder ;
	}

	public Basket deleteProduct(Basket deleteProduct) {
		basketRepository.delete(deleteProduct);
		deleteProduct.setId(0);
		deleteProduct.setName(null);
		deleteProduct.setIngredients(null);
		deleteProduct.setPrice(null);
		deleteProduct.setSize(null);
		deleteProduct.setQuantity(0);
		deleteProduct.setUserId(0);
		return deleteProduct;
	}

	public Basket createProduct(Basket newProduct) {
		return basketRepository.save(newProduct);
	}

	/*
	public Basket createOrder(Basket newProduct) {
		return orderRepository.save(newProduct);
	}
	*/


	


}
