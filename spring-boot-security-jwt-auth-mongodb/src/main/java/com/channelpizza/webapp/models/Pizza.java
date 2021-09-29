package com.channelpizza.webapp.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A pizza represents an item which can be ordered by a user
 * @author Melih Yayli
 */
@Document(collection = "pizzas")
public class Pizza {
//fields
  @Id
  private String id;

  @NotBlank
  @NotNull
  @Size(max = 32)
  @Indexed(unique = true)
  private String pizzaName;

  private double pizzaPrice;

  @NotBlank
  @NotNull
  @Size(max = 64)
  private String pizzaIngredients;

  private String pizzaImageUrl;

  /**
   * Initializes a pizza
   */
  public Pizza() {
  }

  /**
   * Constructs and initializes a pizza based on name, price, ingredients and an image
   * @param pizzaName
   * @param pizzaPrice
   * @param pizzaIngredients
   * @param pizzaImageUrl
   */
  public Pizza(String pizzaName, double pizzaPrice, String pizzaIngredients, String pizzaImageUrl) {
    this.pizzaName = pizzaName;
    this.pizzaIngredients = pizzaIngredients;
    this.pizzaPrice = pizzaPrice;
    this.pizzaImageUrl = pizzaImageUrl;
  }

  /**
   * Retrieves the id of a pizza
   * @return id of pizza
   */
  public String getId() {
    return id;
  }

  /**
   * Retrieves the name of a pizza
   * @return name of pizza
   */
  public String getPizzaName() {
    return pizzaName;
  }

  /**
   * Sets the name of a pizza
   * @return {void}
   */
  public void setPizzaName(String pizzaName) {
    this.pizzaName = pizzaName;
  }

  /**
   * Retrieves the price of a pizza
   * @return price of a pizza
   */
  public double getPizzaPrice() {
    return pizzaPrice;
  }

  /**
   * Sets the price of a pizza
   * @return {void}
   */
  public void setPizzaPrice(double pizzaPrice) {
    this.pizzaPrice = pizzaPrice;
  }

  /**
   * Retrieves the ingredients of a pizza
   * @return ingredients of pizza
   */
  public String getPizzaIngredients() {
    return pizzaIngredients;
  }

  /**
   * Sets the ingredients of a pizza
   * @param pizzaIngredients
   */
  public void setPizzaIngredients(String pizzaIngredients) {
    this.pizzaIngredients = pizzaIngredients;
  }

  /**
   * Retrieves the image URL of a pizza
   * @return image URL of a pizza
   */
  public String getPizzaImageUrl() {
    return pizzaImageUrl;
  }

  /**
   * Sets the image of a pizza
   * @param pizzaImageUrl
   */
  public void setPizzaImageUrl(String pizzaImageUrl) {
    this.pizzaImageUrl = pizzaImageUrl;
  }

}
