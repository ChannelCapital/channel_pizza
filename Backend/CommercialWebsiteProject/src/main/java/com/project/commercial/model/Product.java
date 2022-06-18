package com.project.commercial.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

/**
 * The pizzas are the products and they are created with the features below on productDao.
 * @author fatih akguc
 */


@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Document(collection = "Product")
public class Product {
	
	@Transient
	public static final String SEQUENCE_NAME = "product_sequence";
	
	@Id
	private int id;
	private String name;
	private String ingredients;
	private String sizes;
	private String prices;
	private List<String> size;
	private List<Double> price;
	
	

	public Product() {
		super();
	}


	public Product(int id, String name, String ingredients, String sizes, String prices) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.sizes = sizes;
		this.prices = prices;
	}


	/*public Product(String name, String ingredients, List<String> size, List<Double> price) {
		super();
		this.name = name;
		this.ingredients = ingredients;
		this.size = size;
		this.price = price;
	}



	public Product(int id, String name, String ingredients, List<String> size, List<Double> price) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.size = size;
		this.price = price;
	}*/



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getIngredients() {
		return ingredients;
	}



	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}






	public List<String> getSize() {
		return size;
	}



	public void setSize(List<String> size) {
		this.size = size;
	}



	public List<Double> getPrice() {
		return price;
	}



	public void setPrice(List<Double> price) {
		this.price = price;
	}



	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}



	public String getSizes() {
		return sizes;
	}


	public void setSizes(String sizes) {
		this.sizes = sizes;
	}


	public String getPrizes() {
		return prices;
	}



	public void setPrizes(String prizes) {
		this.prices = prizes;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", sizes=" + sizes
				+ ", prices=" + prices + "]";
	}








	

}
