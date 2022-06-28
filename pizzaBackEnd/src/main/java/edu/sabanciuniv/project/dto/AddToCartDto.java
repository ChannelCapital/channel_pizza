package edu.sabanciuniv.project.dto;

public class AddToCartDto {

	private String id;
	private String productId;
	private Integer quantity;
	
	public AddToCartDto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "AddToCartDto [id=" + id + ", productId=" + productId + ", quantity=" + quantity + "]";
	}
	
	

}
