import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView'
import ProductsView from '../views/ProductsView.vue'
import SignupView from '../views/SignupView.vue'
import BasketView from '../views/BasketView.vue'
import LoginView from '../views/LoginView.vue'
import SecureComponent from "../views/Secure.vue"



Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
    
  },
 
  {
    path: '/signup',
    name: 'signup',
    component: () => import(/* webpackChunkName: "about" */ '../views/SignupView.vue')
  },

 
  {
    path: '/basket',
    name: 'basket',
    component: () => import(/* webpackChunkName: "about" */ '../views/BasketView.vue')
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue')
  },
  {
    path: "/secure/:id",
    name: "secure",
    component: SecureComponent
    //component: () => import(/* webpackChunkName: "about" */ '../views/Secure.vue')

},
  {
    path: '/products',
    name: 'products',
    component: () => import(/* webpackChunkName: "about" */ '../views/ProductsView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
