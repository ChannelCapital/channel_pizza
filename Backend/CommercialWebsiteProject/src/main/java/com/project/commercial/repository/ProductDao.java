package com.project.commercial.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.project.commercial.model.Product;

/**
 * This repository creates the product objects and be used for displaying them.
 * @author fatih akguc
 */

@Component
public class ProductDao {
	
	
	
	private List<Product> products = new ArrayList<>();

	public ProductDao() {
		
		Product pizza1 = new Product(1,"Social Pizza", "Pepperoni, corn, mozarella","S / M / L","3£ / 4£ / 5£");
		Product pizza2 = new Product(2,"3 Cheese Pizza", "Cheddar, Gouda, and Mozzarella","S / M / L","3.5£ / 4.5£ / 5.5£");
		Product pizza3 = new Product(3,"Peperoni Pizza", "Pepperoni, mozarella, olive, sausage","S / M / L","2.5£ / 3.5£ / 4.5£");
		Product pizza4 = new Product(4,"Mixed Pizza", "Pepperoni, corn, mozarella","S / M / L","4£ / 4,5£ / 5.5£");
		Product pizza5 = new Product(5,"4 Season Pizza", "Pastrami, sausage, mushroom,mozarella","S / M / L","5£ / 5.5£ / 6£");

		products.add(pizza1);
		products.add(pizza2);
		products.add(pizza3);
		products.add(pizza4);
		products.add(pizza5);
		
		System.out.println("Userdao worked!");
		for (Product product : products) {
			System.out.println(product.toString());
		}
	}
	
	public List<Product> findAll(){
		return products;
	}
	
	//It can be added if needed.
	/*
	public Product findProductById(long id) {
		for (Product product : products) {
			if(product.getId()==id) {
				return product;
			}
		}
		return null;
	}*/
	
	
}
