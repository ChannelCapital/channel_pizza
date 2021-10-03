<template>
  <div class="container">
     <div class="page-centered">
            <h1 class="login-h1">Login to your Account</h1>
            <form>
                <div class="form-group">
                    <input type="username" placeholder="Username" required v-model=username class="form-control form-input">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" required v-model=password class="form-control form-input">
                </div>
                <div><button type="button" @click="submitlogin()">Login</button></div>
                <p v-if="canLoggedIn">Please enter a correct username and password.</p>
                <a class="nav-link" v-if="canLoggedIn" href="/register">Register Here</a>
            </form>
        </div>
  </div>
</template>

<script>


import axios from 'axios'

export default {
    name: "Login",
    data(){
      return{
        username: "",
        password: "",
        canLoggedIn: false,
      };
    },

    methods: {
       submitlogin() {
        axios.post("http://localhost:8080/users/login/" + this.username + "/" + this.password).then(response =>{
        if (response.data.id != null) {
        sessionStorage.setItem("id", response.data.id);
        sessionStorage.setItem("username", response.data.username);
        this.$store.commit("setAuthentication", true);
        this.$router.push("/");
        location.reload();
        
      } 
      else if(this.username !="" && this.password !=""){
        this.canLoggedIn = true;
      }
      else {
        this.canLoggedIn = true;
        
      }
        }).catch(error =>{
          console.log(error);
        });
      

    },
  },


    



};





</script>

<style>
.page-centered {
    width: 50%;
    height: 30%;
    position: absolute;
    top: 40%;
    left: 50%;
  font-family: sans-serif;
    -ms-transform: translateX(-50%) translateY(-50%);
    -webkit-transform: translate(-50%,-50%);
    transform: translate(-50%,50%);
}
.login-h1 {
    font-weight: 100;
    font-size: 1.8em;
  font-weight: 100;
    text-align: center;
  font-family: sans-serif;
    margin-bottom: 30px;
}
.form-input {
  width:100%;
  margin-bottom: 10px;
    font-size: 0.85em;
    font-weight: 100;
    border-radius: 0px !important;
    padding: 10px 20px;
}
input, textarea, button, button:focus {
    outline: none;
    box-shadow:none !important;
}

.form-submit {
    background-color: royalblue;
    border: none;
    font-weight: 100;
    font-size: 1em;
    color: white;
    padding: 10px 30px;
    cursor: pointer;
}
</style>