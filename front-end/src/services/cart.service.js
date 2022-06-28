
import axios from 'axios';
import authHeader from './auth-header';
const API_URL = 'http://localhost:8080/api/cart/';
class CartService {
  getCartItems() {
    return axios.get(API_URL + `?userId=${JSON.parse(localStorage.user).id}`, { headers: authHeader() });
  }
  getCartItem(id) {
    return axios.get(API_URL + `${id}?userId=${JSON.parse(localStorage.user).id}`, { headers: authHeader() });
  }
  addToCart(data) {
    return axios.post(API_URL + `add?userId=${JSON.parse(localStorage.user).id}`, data, { headers: authHeader() });
  }
  deleteCartItem(id) {
    return axios.delete(API_URL + `delete/${id}` + `?userId=${JSON.parse(localStorage.user).id}`, { headers: authHeader() });
  }
  deleteAll() {
    return axios.delete(API_URL + `deleteAll` + `?userId=${JSON.parse(localStorage.user).id}`, { headers: authHeader() });
  }
}
export default new CartService();


