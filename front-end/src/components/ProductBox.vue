<template>
  <div class="card text-center h-100 w-100" style="background-color: #d8cfcf">
    <div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
      <img
        class="card-img-top embed-responsive-item"
        :src="product.imageURL"
        alt="Product Image"
      />
    </div>

    <div class="card-body">
      <h5 class="card-title">{{ product.name }}</h5>
      <p class="card-text font-italic">{{ product.description }}</p>
      <h6 class="product-price">{{ product.price }} TL</h6>
      <div class="row">
        <div class="col"><span class="input-group-text" id="basic-addon1">Quantity</span></div>
        <div class="col"><input class="form-control" type="number" v-model="quantity"/></div>
      
      </div>
      <br />
      <div class="row">
        <div class="col-3"></div>
        <div class="col-6"><button
        type="button"
        id="add-to-cart-button"
        class="btn btn-secondary"
        @click="addToCart">
        Add to Cart
        <ion-icon name="cart-outline" v-pre></ion-icon>
      </button></div>
        <div class="col-3"></div>
      </div>
      
    </div>
  </div>
</template>

<script>
import cartService from "../services/cart.service";
import swal from "sweetalert";
export default {
  name: "ProductBox",

  data() {
    return {
      //id : null,
      quantity: 1,
    };
  },
  props: ["product"],

  methods: {
    addToCart() {
      var data = {
        productId: this.product.id,
        quantity: this.quantity,
      };
      cartService
        .addToCart(data)
        .then((response) => {
          swal({
            text: "Pizza added to cart!",
            icon: "success",
          });
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>

<style>
.embed-responsive .card-img-top {
  object-fit: cover;
}
a {
  text-decoration: none;
}
.card-title {
  color: #484848;
  font-size: 1.1rem;
  font-weight: 400;
}
.card-title:hover {
  font-weight: bold;
}
.card-text {
  font-size: 0.9rem;
}
#edit-product {
  float: right;
}
</style>