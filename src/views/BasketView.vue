<template>
  <div class="container">
    <div class="row">  
      <div class="container"> 
          <h1>Basket List</h1>
        </div> 
      </div>
      <div class="row">
      <div class="col-md-11">
      <table class="table table-striped table-dark">
        <thead>      
        <tr>
          <td>Product Id</td>
          <td>Name</td>
          <td>Ingredients</td>
          <td>Size</td>
          <td>Price</td>
          <td>Quantity</td>
          <td>Change</td>
        </tr>
        </thead>
        <tbody>
            <tr v-for = "product in basket" v-bind:key = "product.id">
              <td>{{product.id}}</td>
              <td>{{product.name}}</td>
              <td>{{product.ingredients}}</td>
              <td>{{product.size}}</td>
              <td>{{product.price}}</td>
              <td>{{product.quantity}}</td>
              <td>
                <form class="row g-3" id="buttonUpdate">
                  <div class="col-md-1">
                    <button type="submit" class="btn btn-primary" @click.prevent.stop="updateRecord(product)">Update</button>
                  </div>
                  </form>
                </td>
                <td>
                  <form class="row g-3" id="buttonsSave">
                    <div class="col-md-2">
                    <button type="submit" class="btn btn-primary" @click.prevent.stop="deleteRecord(product.id)">Delete</button>
                    </div>
                </form>
              </td>
          </tr>
        </tbody>
      </table>
     </div>
     <div class="col-md-1">   
            <table class="table table-striped table-dark">
              <thead>      
                <tr>
                  <td>Change Quantity</td>
                </tr>
              </thead>
              <tbody>
                <input type="number" id= "number" value="" min="1" max="100" step="1" v-model="quantity"/>
              </tbody>
            </table>
          </div>
        </div>  
        <div class="container">
          <div class="row">
          <span class="col-md-3">
           <h2>Total Price:</h2>
          </span> 
          <span class="col-md-2">
          <p>{{ calculate() }} £</p>
          </span> 
          <span class="col-md-4">
          <button type="submit" class="btn btn-danger" @click.prevent.stop="order()">Order Now!</button>
        </span>
      </div>
      </div>
    </div>
</template>


<script>
import services from "../services";

export default {
  name: "BasketView",
  data(){
    return{
    basket:[],
    quantity:'',
    totalCost:''
    }
  },
  methods:{
  /**
  * Creates an order and delete products in basket
  * @author: fatih akguc
  */
    order(){

    if (this.basket.length === 0){
      alert('To order please add product to basket first!');

    }else{
    const axios = require("axios");
    axios.post("http://localhost:8080/createOrder/"+ this.totalCost)
       .then(function (response) {
        
        })
         alert('Thank You! Your Order is Completed. You can pay it at the door.');
        this.$router.push('/products');

         axios.post(`http://localhost:8080/deleteBasket`)
        .then(function (response) {
       
          })
    }
    },
  /**
  * Calculates the total price of the products in the basket
  * @author: fatih akguc
  * @param {price} the price of the products
  * @param {quantity} the quantity of the 
  * @param {totalCost} totalCost of the product in the basket
  * @return {total} the total cost of the products 
  */
   calculate(){
     let total = 0;
     for (let index = 0; index < this.basket.length; index++) {
       let price = this.basket[index].price;
       let quantity = this.basket[index].quantity;
       total += price * quantity;       
     }
     this.totalCost = total;
     return total;
     
   },
  /**
    * Update the quantity of the product in the basket
    * @author: fatih akguc
   */
    updateRecord(product){

       if(this.quantity == ''){
          alert('First enter the quantity to update please.');
        }else{
      const axios = require("axios");
      axios.put("http://localhost:8080/updateProduct/"+product.id+"/"+this.quantity)
      .then(function (response) {
         alert('Updated. Want to add more Pizza?');
        
        })
        this.$router.push('/products');
        }
  },
  /**
    * Delete the product in the basket
    * @author: fatih akguc
   */
  deleteRecord(id){
      const axios = require("axios");
      axios.post(`http://localhost:8080/deleteProduct/${id}`)
      .then(function (response) {
        })
        alert('Deleted. Add another pizza to Basket!');
        this.$router.push('/products');
      
  }}, 
  /**
    * When the page is created this method calls the products user selected
    * @author: fatih akguc
   */
  created(){
    const userId = sessionStorage.getItem("userId");
    const axios = require("axios");
      axios.post("http://localhost:8080/orders/"+userId).then((response) =>{
        this.basket= response.data;
      });
    }
  }

</script>


<style scoped>

p{
  font-size: 30px;
}

h1,h2{
  align-content: center;
  color: white;
}
 
h3 {
  color: white;
  font-size: 15px;
  
}

.col-md-4 {
  text-align: center;
  
}
.col-md-6 {
  width: 100%;
}

table{
  color: white;
  text-align: left;
  align-content: left;
}

.updatebtn,.deletebtn{
  margin-left: 10%;
  
}

div.col-md-2{
 margin-left: 5%;

}

div.col-md-1{
 text-align: center;
 width: 1px;

}

#number{
  margin-left: 20%;
}




</style>
