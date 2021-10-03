<template>
    <div class="container">
        
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">Your Pizza Shop</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-link active" aria-current="page" href="/">Home</a>
        <a class="nav-link" v-if=isLoggedin href="/orders">Orders</a>
        <a class="nav-link" v-if ="!isLoggedin" href="/login">Login</a>
        <a class="nav-link" v-if ="!isLoggedin" href="/register">Register</a>        
        <a class="nav-link" href="/contact">Contact</a>        
        <a class="nav-item nav-link disabled" v-if="isLoggedin" href="#" tabindex="-1" aria-disabled="true">Welcome {{userName}}</a>
        <a class="nav-link" v-if="isLoggedin" @click="logOut()" href="#">Logout</a>
      </div>
    </div>
  </div>
</nav>

<div class= "row">
  <div class="col" >
    <div class = "row" v-for="pizza in pizzaList" v-bind:key = pizza.id>
      <div class= "col">
    <div class="card">
      <img v-bind:src="pizza.image" class="card-img-top" alt="pizza">
      <div class="card-body">
        <h5 class="card-title">{{pizza.name}}</h5>
        <p class="card-text">{{pizza.price}} €</p>
        <p class="card-text">Size {{pizza.size}}</p>
        <a href="#" class="btn btn-primary" v-if="isLoggedin" @click="addPizzaToCart(pizza.id)" >Add To Cart</a>
        </div>
      </div>
    </div>
    </div>
  </div>
  
    
    
      
          <div class="col">
           
          
          <div class="card p-8 bg-white w-full max-w-xl my-10 mx-auto" v-if="pizzaUserCart.length>0" >
    <h1 class="text-center mb-8 font-bold py-3 text-2xl" v-if="pizzaUserCart.length>0">Your Pizza Cart</h1>
    <dl class="menu-list">
      <div class="menu-list-item" v-for="pizza in pizzaUserCart" v-bind:key = pizza.id>
        <dt>{{pizza.name}}</dt>
        <dd><i class="fas fa-dollar-sign"></i>{{pizza.price}} € <button @click="removePizzaFromCart(pizza.id)">remove</button> </dd>
      </div>
      
    </dl>
  </div>
  <div v-if="pizzaUserCart.length>0"><dd><i class="fas fa-dollar-sign"></i>Order Total Price : {{orderTotalPrice}} € </dd></div>
  <div><button v-if="pizzaUserCart.length>0" type="button" @click="giveOrder()" >Give Order</button></div>      
      </div>
    
  </div>
  
    
    </div>
    
    
</template>

<script>

import axios from 'axios'

    export default {
        name:'pizzaList',
        data(){
            return {pizzaList : [],
            pizzaUserID: "",
            userName: "",
            isLoggedin: false,
            pizzaUserCart: [],
            orderTotalPrice:""
            }
        },
        
        methods:{
            getAllPizzas(){
                axios.get("http://localhost:8080/pizzas/allpizzas").then((Response) =>{
                    this.pizzaList = Response.data;
                })
            },
            logOut(){
              this.$store.commit("setAuthentication", true);
              sessionStorage.removeItem("id");
              sessionStorage.removeItem("username");
              sessionStorage.clear();
              this.isLoggedin=false;
              this.pizzaUserCart = [];
              
              
            },
            getUserCart(){

              axios.get("http://localhost:8080/users/getcartbypizzauserid/" + this.pizzaUserID)
              .then(Response =>{this.pizzaUserCart = Response.data;})
            },

            addPizzaToCart(pizzaid){
              this.getUserCart();
              this.getOrderTotalPrice();
              axios.post("http://localhost:8080/users/addpizzatocart/"+this.pizzaUserID+"/"+pizzaid);              
              this.getUserCart();
              this.getOrderTotalPrice();
              //location.reload();
              
            },
            removePizzaFromCart(pizzaid){
              this.getUserCart();
              this.getOrderTotalPrice();
              axios.post("http://localhost:8080/users/removepizzafromcart/"+this.pizzaUserID + "/" +pizzaid);
              this.getUserCart();
              this.getOrderTotalPrice();
              //location.reload();
              
            },
            getOrderTotalPrice(){
              axios.get("http://localhost:8080/users/getordertotalprice/" + this.pizzaUserID)
              .then(Response =>{this.orderTotalPrice = Response.data;})
              
            },
            giveOrder(){
              axios.post("http://localhost:8080/orders/addpizzaorder/" + this.pizzaUserID)
              this.pizzaUserCart=[]
            }
            
            }
        ,
        created(){
    
    this.$store.commit("setAuthentication", true);
    const Responseid = sessionStorage.getItem("id");
    const Responseusername = sessionStorage.getItem("username");
    
    if (Responseid != null && Responseusername != null){
      this.pizzaUserID = Responseid;
      this.userName = Responseusername;
      this.isLoggedin = true;
    }
    


  this.getAllPizzas()
  this.getUserCart()
  this.getOrderTotalPrice()
        },
    
    

    }
</script>

<style>
body{
  background-color: #EEE;
}



h1{
  font-family: 'pt-sans-narrow', sans-serif;
  border-top: 2px solid #000;
  border-bottom: 2px solid #000;
}

.menu-list {
  font-family: monospace;
  font-size: 20px;
}
dl.menu-list .menu-list-item {
  display: flex;
  margin: 5px 0px 5px 0px;
}
dl.menu-list dt {
  flex: 0 1 auto;
  margin: 0px 0px 0px 0px;
  order: 1;
}
dl.menu-list .menu-list-item:after {
  
  background-image:  radial-gradient(circle, rgba(0,0,0,1) 0%, rgba(0,0,0,1) 24%, rgba(255,255,255,0) 25%, rgba(255,255,255,0) 100%);
  background-size: .5em .5rem;
  background-repeat: repeat-x;
  background-position: left bottom;
  content: "";
  display: block;
  flex: 1 1 auto;
  margin: 0px 12px 5px 12px;
  order: 2;
}
dl.menu-list dd {
  flex: 0 1 auto;
  margin: 0px 0px 0px 0px;
  order: 3;
}
</style>