import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';

Vue.use(Router);

export const router = new Router({
  //mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: Register
    },
    {
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('./views/Profile.vue')
    },
    {
      path: '/products',
      name: 'products',
      // lazy-loaded
      component: () => import('./views/ProductsList.vue')
    },
    {
      path: '/details/:id',
      name: 'details',
      // lazy-loaded
      component: () => import('./views/ProductDetails.vue')
    },
    {
      path: '/add',
      name: 'add',
      // lazy-loaded
      component: () => import('./views/AddProduct.vue')
    },
    {
      path: '/cart',
      name: 'cart',
      // lazy-loaded
      component: () => import('./views/Cart.vue')
    },
    {
      path: '/address',
      name: 'address',
      // lazy-loaded
      component: () => import('./views/UserAddress.vue')
    },
    {
      path: '/address/:id',
      name: 'updateAddress',
      // lazy-loaded
      component: () => import('./views/UpdateAddress.vue')
    },
  ]
});
router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register', '/home'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');
  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next('/login');
  } else {
    next();
  }
});

