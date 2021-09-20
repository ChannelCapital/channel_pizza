import { createWebHistory, createRouter } from "vue-router";
import Home from "./components/Home.vue";
import Login from "./components/Login.vue";
import Register from "./components/Register.vue";
// lazy-loaded
const Profile = () => import("./components/Profile.vue")
const BoardPizzaCreate = () => import("./components/BoardPizzaCreate.vue")
const BoardUser = () => import("./components/BoardUser.vue")
const Pizza = () => import("./components/Pizza.vue")

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/home",
    component: Home,
  },
  {
    path: "/login",
    component: Login,
  },
  {
    path: "/register",
    component: Register,
  },
  {
    path: "/profile",
    name: "profile",
    // lazy-loaded
    component: Profile,
  },
  {
    path: "/create-pizza",
    name: "createpizza",
    // lazy-loaded
    component: BoardPizzaCreate,
  },
  {
    path: "/user",
    name: "user",
    // lazy-loaded
    component: BoardUser,
  },
  {
    path: "/pizza/:id",
    name: "pizza",
    // lazy-loaded
    component: Pizza,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;