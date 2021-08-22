package com.channelpizza.webapp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "pizzas")
public class Pizza {
  @Id
  private String id;

  @NotBlank
  @NotNull
  @Size(max = 32)
  private String pizzaName;

  private double pizzaPrice;

  @NotBlank
  @NotNull
  @Size(max = 64)
  private String pizzaIngredients;

  private String pizzaImageUrl;

  private Integer quantity;

  public Pizza() {
  }

  public Pizza(String pizzaName, double pizzaPrice, String pizzaIngredients, String pizzaImageUrl, Integer quantity) {
    this.pizzaName = pizzaName;
    this.pizzaIngredients = pizzaIngredients;
    this.pizzaPrice = pizzaPrice;
    this.pizzaImageUrl = pizzaImageUrl;
    this.quantity = quantity;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPizzaName() {
    return pizzaName;
  }

  public void setPizzaName(String pizzaName) {
    this.pizzaName = pizzaName;
  }

  public double getPizzaPrice() {
    return pizzaPrice;
  }

  public void setPizzaPrice(double pizzaPrice) {
    this.pizzaPrice = pizzaPrice;
  }

  public String getPizzaIngredients() {
    return pizzaIngredients;
  }

  public void setPizzaIngredients(String pizzaIngredients) {
    this.pizzaIngredients = pizzaIngredients;
  }

  public String getPizzaImageUrl() {
    return pizzaImageUrl;
  }

  public void setPizzaImageUrl(String pizzaImageUrl) {
    this.pizzaImageUrl = pizzaImageUrl;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
