import axios from 'axios';
import authHeader from './auth-header';
const API_URL = 'http://localhost:8080/api/test/';
class ProductService {
  getAll() {
    return axios.get(API_URL + 'all');
  }
  getProductsList() {
    return axios.get(API_URL + 'products', { headers: authHeader() });
  }
  getProductDetails(id) {
    return axios.get(API_URL + `details/${id}`, { headers: authHeader() });
  }
  create(data) {
    return axios.post(API_URL + 'add', data, { headers: authHeader() });
  }
  getProductsPublished() {
    return axios.get(API_URL + 'details/published', { headers: authHeader() });
  }
  update(id, data) {
    return axios.put(API_URL + `details/${id}`, data, { headers: authHeader() });
  }
  delete(id) {
    return axios.delete(API_URL + `details/${id}`, { headers: authHeader() });
  }
  deleteAll() {
    return axios.delete(API_URL + `products`, { headers: authHeader() });
  }
  findByName(name) {
    return axios.get(API_URL + `products?name=${name}`, { headers: authHeader() });
  }
  
}
export default new ProductService();



