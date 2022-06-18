import axios from 'axios'

const USERDATA = 'http://localhost:8080/users'
const PRODUCTDATA = 'http://localhost:8080/products'
const BASKETDATA = 'http://localhost:8080/orders'


const service = {
    getUsers(){
        return axios.get(USERDATA);
      },
      getProducts(){
        return axios.get(PRODUCTDATA);
    },
      getBasket(){
        return axios.get(BASKETDATA);
      }
}


export default service;

