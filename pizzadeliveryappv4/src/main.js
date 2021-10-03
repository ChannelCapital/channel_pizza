import Vue from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import VueRouter from 'vue-router'
import 'mutationobserver-shim'
import Vuex from 'vuex';


Vue.use(Vuex)
Vue.use(VueRouter)

import Home from './components/Home.vue'
import Login from './components/Login.vue'
import Register from './components/Register.vue'
import Orders from './components/Orders.vue'
import Contact from './components/Contact.vue'

Vue.config.productionTip = false


const router = new VueRouter({
  routes: [{
    path: '/', component: Home    
  },{
    path: '/login', component: Login
  },{
    path: '/register', component: Register 
  },
  {
    path: '/orders', component: Orders
  },
  {
    path: '/contact', component: Contact
  }
],
  mode: 'history'
})

const store = new Vuex.Store({
  state: {
    authenticated: false
  },
  mutations: {
    setAuthentication(state,status){
     state.authenticated = status;
  }
  },
  actions: {
  },
  modules: {
  }
})



new Vue({
  render: h => h(App),
  router: router,
  store: store
}).$mount('#app')
