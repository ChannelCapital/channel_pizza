import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/v1/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'pizzas');
  }
  getPizzaContent(pizzaId) {
    return axios.get(API_URL + 'pizzas/' + pizzaId);
  }
  getPizzaByIngredient(pizzaIngredient) {
    return axios.get(API_URL + 'pizzas/ingredients/' + pizzaIngredient);
  }

  postOrder(order, currentUserId) {
    let orderedItems = [];

    for (let index = 0; index < order.length; index++) {
      orderedItems.push({
        quantity: parseInt(order[index].quantity), 
        pizzaPrice: order[index].pizzaPrice,
        pizzaId: order[index].id,
        pizzaName: order[index].pizzaName
      });
      console.log(order[index]);
    }

    let orderItems = {
      userId: currentUserId,
      orderItem: orderedItems
    }

    return axios.post(API_URL + 'orders', orderItems, { headers: authHeader() })
  }

  getOpenOrders(currentUserId) {
    // console.log("getOpenOrders UserId:" + currentUserId);
    return axios.get(API_URL + 'orders/' + currentUserId, { headers: authHeader() });
  }

  createPizza(pizza) {

    return axios.post(API_URL + 'pizzas', pizza, { headers: authHeader() })

  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }
}

export default new UserService();
