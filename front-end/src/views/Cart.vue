<template>
  <div class="container">
    <div
      v-for="cartItem in cartItems"
      :key="cartItem.product.id"
      class="row mt-2 pt-3 justify-content-around"
    >
      <div class="row">
        <div class="col-6">
          <img
            v-bind:src="cartItem.product.imageURL"
            alt="product-picture"
            style="width: 100%"
            class="w-25 card-img-top embed-responsive-item"
            
          />
          <p id="item-name" class="mb-0 font-weight-bold" style="float: right">
            {{ cartItem.product.name }}
          </p>
        </div>
        <div class="col-3">
          <a
            href="#"
            class="text-right"
            style="float: right"
            @click="deleteCartItem(cartItem.id)"
          >
            Delete</a
          >
        </div>
        <div class="col-3">
          <p id="item-price" class="mb-0 font-weight-bold" style="float: right">
            Price: {{ cartItem.product.price }} TL
          </p>
          <br />
          <p id="item-quantity" class="mb-0" style="float: right">
            Quantity : {{ cartItem.quantity }}
          </p>
        </div>
        <br />
      </div>
    </div>
    <div class="total-cost pt-2 text-right">
      <h5>Total : {{ totalCost }} TL</h5>
      <br />
          <a v-if="!address" class="btn btn-danger" style="margin-right: 10px" :href="`#/address`">
            Add Address
          </a>
          <a v-else class="btn btn-danger" style="margin-right: 10px" :href="`#/address/${address.id}`" >
            Update Address
          </a>
          <button v-show="address && totalCost>0" class="m-6 btn btn-md btn-success" style="width: 25%" @click="makeOrder">
            ORDER</button>
          </div>
  </div>
</template>

<script>
import cartService from "../services/cart.service";
import userService from "../services/user.service";
import swal from "sweetalert";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Cart",
  data() {
    return {
      cartItems: [],
      totalCost: 0,
      address: null,
    };
  },
  methods: {
    listCartItems() {
      cartService
        .getCartItems()
        .then((response) => {
          this.cartItems = response.data.cartItems;
          this.totalCost = response.data.totalCost;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    getAddress() {
      userService
        .getAdress()
        .then((response) => {
          this.address = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    deleteCartItem(itemId) {
      cartService
        .deleteCartItem(itemId)
        .then((response) => {
          console.log(response.data);
          swal({
            text: "Deleted!",
            icon: "info",
          });
        })
        .catch((e) => {
          console.log(e);
        });
    },
    makeOrder(){
   swal({
            title: "Enjoy!",
            text: "Order has been placed",
            icon: "success",
          });
          cartService.deleteAll();
          this.$router.go(-1);
       
    }
  },
  mounted() {
    //this.userId = JSON.parse(localStorage.user).id;
    this.listCartItems();
    this.getAddress();
  },
};
</script>

<style scoped>

h5 {
  font-family: "Roboto", sans-serif;
  color: #484848;
  font-weight: 700;
}
.embed-responsive .card-img-top {
  object-fit: cover;
}
#item-price {
  color: #232f3e;
}
#item-quantity {
  float: left;
}
</style>