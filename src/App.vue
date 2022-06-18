<template>
  <div id="app">
    <div class="nav">
    <router-link v-if="authenticated" to="/login" style="color:white; font-size:20px;" v-on:click.native="logout()" replace > Logout </router-link>
    </div>
     <div class="header" v-if="!authenticated">
    <Header></Header>
    </div>
    <div class="header" v-if="authenticated">
    <HeaderSecure></HeaderSecure>
    </div>
    <router-view @authenticated="setAuthenticated" ></router-view>
  </div>
</template>

<script>
import Header from './components/Header.vue'
import HeaderSecure from './components/HeaderSecure.vue'

export default {
   components: {
    Header,
    HeaderSecure,
  },
 name: 'App',
 /**
    * When the home page is called this methods checks authantication is true or false.
    * If it is false home pagei login and signup page can be displayed.
    * But if user logs in it becomes true and user can see the secure profile, products and basket pages.
    * @author: fatih akguc
   */
        data() {
            return {
                authenticated: false,
                
            }
        },
        mounted() {
            if(!this.authenticated) {
                this.$router.replace({ name: "home" });
            }
        },
        methods: {
            setAuthenticated(status) {
                this.authenticated = status;
            },
            logout() {
                this.authenticated = false;
                this.$router.push({path: "/"});
            }
        }
    }
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 1%;
  
}

#avatar{
  width: 25%;
}

</style>
