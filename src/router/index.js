import Vue from 'vue'
import VueRouter from 'vue-router'
import Favorite from '../views/Favorite.vue'
import Profile from '../views/Profile.vue'
import Basket from '../views/Basket.vue'
import SignIn from '../views/SignIn.vue'
import SignUp from '../views/SignUp.vue'
import Order from '../views/Order.vue'
import Home from '../views/Home.vue'
import store from '../store'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
    beforeEnter: (to, from, next) => {
      if(store.state.authenticated == false){

        next("/");
      }
      else{
        next();
      }


    }
  },
  {
    path: '/basket',
    name: 'Basket',
    component: Basket,
    beforeEnter: (to, from, next) => {
      if(store.state.authenticated == false){

        next("/");
      }
      else{
        next();
      }


    }
  },
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn,
    beforeEnter: (to, from, next) => {
      if(store.state.authenticated == false){

        next("/");
      }
      else{
        next();
      }


    }
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp,
    beforeEnter: (to, from, next) => {
      if(store.state.authenticated == false){

        next("/");
      }
      else{
        next();
      }


    }
  },
  {
    path: '/favorite',
    name: 'Favorite',
    component: Favorite,
    beforeEnter: (to, from, next) => {
      if(store.state.authenticated == false){

        next("/");
      }
      else{
        next();
      }


    }
  }
  ,
  {
    path: '/order',
    name: 'Order',
    component: Order,
    beforeEnter: (to, from, next) => {
      if(store.state.authenticated == false){

        next("/");
      }
      else{
        next();
      }


    }
  }
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router
