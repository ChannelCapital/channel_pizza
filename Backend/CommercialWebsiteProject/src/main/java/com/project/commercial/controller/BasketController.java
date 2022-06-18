package com.project.commercial.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.commercial.model.Basket;
import com.project.commercial.repository.BasketRepository;
import com.project.commercial.service.BasketService;


/**
 * Listing, creating, updating and deleting process of the product in the basket is
 * controlled on this class by connecting with basket serivce. 8081 port is for frontend
 *  vue application port and we allowed this port in here.  
 * @author fatih akguc
 * @return ok, 200 response when methods are executed.

 */


@RestController
@RequestMapping("")
@CrossOrigin("http://localhost:8081")
public class BasketController {
	
	@Autowired
	private BasketRepository basketRepository;
	private BasketService basketService;
	
	@Autowired
	private SequenceGeneratorService service;
	
	public BasketController(BasketRepository basketRepository, BasketService basketService) {
		super();
		this.basketRepository = basketRepository;
		this.basketService = basketService;
	}
	
	@GetMapping("/basket")
	public List<Basket> findAllOrders(){
		return basketRepository.findAll();
	}
	
	@PostMapping("/orders/{userId}")
	public List<Basket> findBasketById(@PathVariable("userId") int userId) {
		List<Basket> wholeBasket = findAllOrders();
		List<Basket> newBasket = new ArrayList<>();
		for (Basket basket : wholeBasket) {
			if(basket.getUserId() == userId) {
				newBasket.add(basket);	
			}
		}
		return newBasket;
	}
	
	@PostMapping("/addProduct/{id}/{name}/{ingredients}/{size}/{price}/{quantity}/{userId}")
	public ResponseEntity<Basket> addProduct(@PathVariable("id") int id, @PathVariable("name") String name,
			@PathVariable("ingredients") String ingredients, @PathVariable("size") String size,
			@PathVariable("price") Double price, @PathVariable("quantity") int quantity,
			@PathVariable("userId") Integer userId){
		Basket newProduct = new Basket(id, name, ingredients, size, price, quantity, userId);
		newProduct.setId(service.generateSequence(Basket.SEQUENCE_NAME));
		return ResponseEntity.ok(basketService.createProduct(newProduct));
		
	}
	
	@PutMapping("/updateProduct/{id}/{quantity}")
	public ResponseEntity<Basket> updateProduct(@PathVariable("id") int id, @PathVariable("quantity") int quantity){
		return ResponseEntity.ok(basketService.updateOrder(id,quantity));
		
	}
	
	@PostMapping("/deleteProduct/{id}")
	public ResponseEntity<Basket> deleteProduct(@PathVariable("id") int id) {
		Basket deleteProduct = basketService.findOrderById(id);
		return ResponseEntity.ok(basketService.deleteProduct(deleteProduct));
		
	}
	
	@PostMapping("/deleteBasket")
	public ResponseEntity<List<Basket>> deleteBasket() {
		List<Basket> basket = findAllOrders();
		for (Basket item : basket) {
			Basket deleteProduct = basketService.findOrderById(item.getId());	
			basketService.deleteProduct(deleteProduct);
			}
		return ResponseEntity.ok(basket);	
	}
}
