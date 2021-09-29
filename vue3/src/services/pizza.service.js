import axios from 'axios';
import authHeader from './auth-header';
/**
 * API URL under which pizza actions are handled.
 */
const PIZZA_API_URL = 'http://localhost:8080/api/v1/pizzas/';

class PizzaService {
  /**
   * Function to retrieve pizza items
   * @returns Returns pizza items
   */
  getPizzaContent() {
    return axios.get(PIZZA_API_URL);
  }
  /**
   * Function to create pizza
   * @param {object} pizza - Array containing details about pizza
   * @returns {void}
   */
  createPizza(pizza) {
    return axios.post(PIZZA_API_URL, pizza, { headers: authHeader() })
  }

  /**
   * Function to search for pizzas containing ingredients
   * @param {String} pizzaIngredient - String to be used as a search term to filter results 
   * @returns Returns pizza items
   */
  getPizzaByIngredient(pizzaIngredient) {
    return axios.get(PIZZA_API_URL + 'ingredients/' + pizzaIngredient);
  }

}

export default new PizzaService();
