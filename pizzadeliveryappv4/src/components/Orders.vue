<template>
  <div class ="container">
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
        <a class="nav-link" href="#">Contact</a>        
        <a class="nav-item nav-link disabled" v-if="isLoggedin" href="#" tabindex="-1" aria-disabled="true">Welcome {{userName}}</a>
        <a class="nav-link" v-if="isLoggedin" @click="logOut()" href="#">Logout</a>
      </div>
    </div>
  </div>
</nav>
<div class="col">
           
          
          <div class="card p-8 bg-white w-full max-w-xl my-10 mx-auto" v-if="orderList.length>0" >
    <h1 class="text-center mb-8 font-bold py-3 text-2xl" v-if="orderList.length>0">Your Orders</h1>
    <dl class="menu-list" v-for="order in orderList" v-bind:key = order.id >
      <h2>Order Total Price : {{order.orderTotalPrice}}</h2>
        <ul style="list-style-type:none;">
            <li><ul v-for="pizza in order.orderedPizzas" v-bind:key= pizza.id style="list-style-type:none;" >
                <li> <b>Name</b> : {{pizza.name}} <b>Price</b> : {{pizza.price}} € <b>Size</b> : {{pizza.size}} </li>
                </ul>
                </li>            
        </ul>
      </dl>
    
  </div>
       
      </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {

name:'orderList',
data(){
return {
orderList:[],
pizzaUserID: "",
userName: "",
isLoggedin: false,
orderedPizza:[]
}
},

methods:{
        logOut(){
              this.$store.commit("setAuthentication", true);
              sessionStorage.removeItem("id");
              sessionStorage.removeItem("username");
              sessionStorage.clear();
              this.isLoggedin=false;
              this.pizzaUserCart = [];
              
              
            },

            getOrderListbyPizzaUserID(){
                axios.get("http://localhost:8080/orders/getpizzaordersbypizzauserid/" + this.pizzaUserID).then(Response=>{
                    this.orderList=Response.data
                    this.orderedPizza=Response.data.orderedPizzas
                    //console.log(this.orderList)
                    console.log(this.orderedPizza)
                });

            }
            

},

created(){

this.$store.commit("setAuthentication", true);
    const Responseid = sessionStorage.getItem("id");
    const Responseusername = sessionStorage.getItem("username");
    
    if (Responseid != null && Responseusername != null){
      this.pizzaUserID = Responseid;
      this.userName = Responseusername;
      this.isLoggedin = true;
    }

    this.getOrderListbyPizzaUserID()

}

}
</script>

<style>

   
h1{
  font-family: 'pt-sans-narrow', sans-serif;
  border-top: 2px solid #000;
  border-bottom: 2px solid #000;
}
.menu-list {
  font-family: monospace;
  font-size: 20px;
}

ul {
flex: 0 1 auto;
  margin: 0px 0px 0px 0px;
  order: 3;
}
ul li{
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

    

</style>