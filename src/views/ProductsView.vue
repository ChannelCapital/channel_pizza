<template>
  <div class="menu">
    <div class="container">
      <div class="title">
        <h1>Menu</h1>
      </div><br>
      <div class="container">
        <div class="row">
          <!--<div class="container">-->
            <div class="col-md-3">
            
            <!--  <div>
              <img alt="pizza1" src="../pictures/pizza1.png" id="pizza1" /><br><br>
              </div>
              <div>
              <img alt="pizza2" src="../pictures/pizza2.png" id="pizza2" /><br><br>
              </div>
              <div>
              <img alt="pizza3" src="../pictures/pizza3.png" id="pizza3" /><br><br>
              </div>
              <div>
              <img alt="pizza4" src="../pictures/pizza4.png" id="pizza4" /><br><br>
              </div>
              <div>
              <img alt="pizza5" src="../pictures/pizza5.png" id="pizza4" /><br><br>
              </div>-->
              <table class="pizzaTable">
                  <thead>  
                    <tr>
                      <img alt="pizza1" src="../pictures/pizza1.png" id="pizza1" /><br><br>
                      </tr>
                      <tr>
                      <img alt="pizza1" src="../pictures/pizza2.png" id="pizza1" /><br><br>
                      </tr>
                      <tr>
                      <img alt="pizza1" src="../pictures/pizza3.png" id="pizza1" /><br><br>
                      </tr>
                      <tr>
                      <img alt="pizza1" src="../pictures/pizza4.png" id="pizza1" /><br><br>
                      </tr>
                      <tr>
                      <img alt="pizza1" src="../pictures/pizza5.png" id="pizza1" /><br><br>
                      </tr>
                  </thead>
              </table>
            </div>
          <div class="col-md-8">
            <table class="table table-striped table-dark">
              <thead>      
                <tr>
                  <td>Name</td>
                  <td>Ingredients</td>
                  <td>Prices</td>
                  <td>Basket</td>
                </tr>
              </thead>
              <tbody>
                <tr v-for = "product in products" v-bind:key = "product.id">
                  <td>{{product.name}}</td>
                  <td>{{product.ingredients}}</td>
                  <td>{{product.prizes}}</td>
                  <td>
                    <span class="updatebtn">
                      <button type="submit" class="btn btn-primary" @click="order(product)">Add</button>
                    </span><br><br>
                   </td>
                   </tr>
                  </tbody>
                </table>
            </div>
          <div class="col-md-1">
            <table class="table table-secondary">
              <thead>      
                <tr>
                  <td>Choose A Size</td>
                </tr>
              </thead>
              <tbody>
                <label for="inputState" class="form-label"></label><br />
                  <select id="inputState" class="form-select" v-model="inputSize">
                    <option selected>Choose...</option>
                    <option>S</option>
                    <option>M</option>
                    <option>L</option>
                  </select>
              </tbody>
            </table>
          </div>
      </div>
      </div>
       </div>
    </div>
  <!--</div>-->

</template>

<script>
import _ from "lodash"
import services from "../services";

export default {
  name: 'ProductsView',
  
  data() {
    return {
      products:[],
      inputSize:'',
      userId:''
    }
  },
  methods: {

    /**
    * This method add the selected products tot the basket to be ordered. This method checks the size and
    * the prices firstand then add the selected ones.
    * @author: fatih akguc
   */

    order(product){

      if(product.id === 1){
        if(product.size === "S"){
          product.price = 3;
        }else if(product.size === "M"){
          product.price = 4;
        }else{
          product.price = 5;
        }
      }else if(product.id === 2){
        if(product.size === "S"){
          product.price = 3.5;
        }else if(product.size === "M"){
          product.price = 4.5;
        }else{
          product.price = 5.5;
        }
        }else if(product.id === 3){
        if(product.size === "S"){
          product.price = 2.5;
        }else if(product.size === "M"){
          product.price = 3.5;
        }else{
          product.price = 4.5;
        }
        }else if(product.id === 4){
        if(product.size === "S"){
          product.price = 4;
        }else if(product.size === "M"){
          product.price = 4.5;
        }else{
          product.price = 5.5;
        }
        }else{
        if(product.size === "S"){
          product.price = 5;
        }else if(product.size === "M"){
          product.price = 5.5;
        }else{
          product.price = 5;
          }
        }

        if(this.inputSize == ''){
          alert('First enter the size please.');
        }else{

      const userId = sessionStorage.getItem("userId");
      const axios = require("axios");
        axios.post("http://localhost:8080/addProduct/"+product.id +"/"+product.name+"/"+product.ingredients+"/"+
                    this.inputSize+"/"+product.price+"/1/"+ userId)
       .then(function (response) {
        })
    }
    alert("Pizza added to the Basket!");
    },

    /**
    *When page is created method lists all the products on the page
    * @author: fatih akguc
   */
  
     getProducts(){
      services.getProducts().then((response) =>{
        this.products = response.data;
      });
    },
  },
  created() {
    this.getProducts();
  }
};
</script>

<style lang="scss" scoped>
  $primary-color: #03a9f4;
  $secondary-color: #404040;
  $bg-color: #286969;
  $text-color: #eaeaea;


@media screen and (min-width: 1200px) {
  table.pizzaTable, .table.table.table-striped.table-dark {
    flex: 50%;
    padding: 5px;
    text-align: center;
    height: 680px;
    #pizza1,#pizza2,#pizza3,#pizza4{
    width: 48%;
    margin-top: 0%;

   }
  }
 }

@media screen and (max-width: 1199px) {
  table.pizzaTable, .table.table.table-striped.table-dark {
    flex: 50%;
    padding: 5px;
    text-align: center;
    height: 534px;
    #pizza1,#pizza2,#pizza3,#pizza4{
      width: 55%;
      margin-top: 15%;
    }
  }
}

 @media screen and (min-width: 992px) {
    table.pizzaTable, .table.table.table-striped.table-dark {
      flex: 50%;
      padding: 5px;
      text-align: center;
      height: 700px;
      #pizza1,#pizza2,#pizza3,#pizza4{
      width: 55%;
      margin-left: 30%;

    }
  }
 }

 @media screen and (max-width: 767px) {
  table.pizzaTable, .table.table.table-striped.table-dark {
    flex: 50%;
    padding: 5px;
    text-align: center;
    height: 650px;
    
    #pizza1,#pizza2,#pizza3,#pizza4{
    width: 100%;
    margin-left: 30%;

    }
  }
}

  h1{
    color: white;
  }

  .col-md-3{
    margin-top: 4%;
    text-align: center;
    
  }

</style>