<template>
  <div>
    <b-navbar variant="primary" type="dark" v-if="id.length == 0">
      <b-navbar-brand tag="h1" class="mb-0" to="/"
        >Channel Pizza</b-navbar-brand
      >
      <b-navbar-nav>
        <b-nav-item to="/signin">Sign In</b-nav-item>
        <b-nav-item to="/signup" exact exact-active-class="active"
          >Sign Up</b-nav-item
        >
      </b-navbar-nav>
    </b-navbar>

    <b-navbar type="dark" variant="primary" v-if="id.length > 0">
      <b-navbar-brand tag="h1" class="mb-0" to="/"
        >Channel Pizza</b-navbar-brand
      >
      <b-navbar-nav>
        <b-nav-item to="/profile">My Profile</b-nav-item>
        <b-nav-item to="/basket">Basket</b-nav-item>
        <b-nav-item to="/favorite">My Favorites</b-nav-item>
        <b-nav-item to="/order">My Order</b-nav-item>
        <b-nav-item href="/" @click="SessionOut()">Log Out</b-nav-item>
      </b-navbar-nav>
    </b-navbar>

    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "navbar",
  data() {
    return {
      id: "",
    };
  },
  components: {},
  created() {
    const res = sessionStorage.getItem("id");

    if (res != null) {
      this.id = res;
    }
  },

  methods: {
    SessionOut() {
      sessionStorage.removeItem("id");
    },
  },
};
</script>