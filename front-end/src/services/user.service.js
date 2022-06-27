import axios from 'axios';
import authHeader from './auth-header';
const API_URL = 'http://localhost:8080/api/user/';
class UserService {
  addAddress(data) {
    return axios.post(API_URL + 'address', data, { headers: authHeader() });
  }
  updateAdress(id, data) {
    return axios.put(API_URL + `address/${id}`, data, { headers: authHeader() });
  }
  getAdress() {
    return axios.get(API_URL + `address?userId=${JSON.parse(localStorage.user).id}`, { headers: authHeader() });
  }
}
export default new UserService();



