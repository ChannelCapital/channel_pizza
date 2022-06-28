package edu.sabanciuniv.project.dto;

import edu.sabanciuniv.project.model.Cart;
import edu.sabanciuniv.project.model.Product;

public class CartItemDto {

	private String id;
	private int quantity;
	private Product product;
	
	public CartItemDto() {
		super();
	}
	
	public CartItemDto(Cart cart) {
		
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@Override
	public String toString() {
		return "CartItemDto [id=" + id + ", quantity=" + quantity + ", product=" + product + "]";
	}
	
	
}
