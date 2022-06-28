<template>
<div id="background-div" class="page-holder bg-cover">
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-dark py-1" >
      <a class="navbar-brand" @click.prevent>
    <img src="./assets/logo.png" width="258" height="110" class="my-image" alt="HunK">
  </a>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/home" class="nav-link">
            Home
          </router-link>
        </li>
        
        <li v-if="showAdminProductsList" class="nav-item">
          <router-link to="/products" class="nav-link">Products List</router-link>
        </li>
        <li v-if="showAdminAddProduct" class="nav-item">
          <router-link to="/add" class="nav-link">Add Product</router-link>
        </li>
        <li class="nav-item">
          <router-link v-if="currentUser" to="/cart" class="nav-link">
          Cart</router-link>
        </li>
      </div>
      <div v-if="!currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/register" class="nav-link">
            <font-awesome-icon icon="user-plus" />Sign Up
          </router-link>
        </li>
        <li class="nav-item">
          <router-link to="/login" class="nav-link">
            <font-awesome-icon icon="sign-in-alt" />Login
          </router-link>
        </li>
      </div>
      <div v-if="currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/profile" class="nav-link">
            <font-awesome-icon icon="user" />
            {{ currentUser.username }}
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" href @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" />LogOut
          </a>
        </li>
      </div>
    </nav>
    <div class="container">
      <router-view />
    </div>
  </div>
</div>
</template>
<script>
export default {
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminProductsList() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }
      return false;
    },
    showAdminAddProduct() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }
      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>
<style>
.page-holder {
    min-height: 100vh;
  }
  .bg-cover {
    background-size: cover !important;
  }
  #background-div {
    background-color: whitesmoke;
  }
  .my-image{
     border-radius: 65px 65px 65px 65px;
    box-shadow: 0px 0px 60px 40px black inset;
    margin: 1vh;
  }
</style>