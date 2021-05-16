<template>
  <b-container class="bv-example-row" style="margin-top: 50px">
    <h3 v-if="!isEmpty">Favorites are Empty</h3>
    <h3 v-if="isEmpty">My Favorites</h3>
    <b-row cols="3">
      <b-col v-for="(pizza, index) in pizzaList" :key="index++">
        <b-card
          :title="pizza.name"
          align="center"
          :img-src="pizza.img"
          img-alt="Image"
          img-top
          tag="article"
          style="max-width: 50rem"
          class="mb-3"
        >
          <b-card-text class="small text-muted">
            {{ pizza.description }}
          </b-card-text>
          <b-card-text> £{{ pizza.price }} </b-card-text>
          <b-button
            @click="AddBasket(pizza.id)"
            style="margin: 10px"
            href="#"
            variant="primary"
            v-if="id.length > 0"
            >Add Basket</b-button
          >
          <b-button
            @click="removeItem(pizza.id)"
            style="margin: 10px"
            href="#"
            variant="primary"
            v-if="id.length > 0"
            >Remove</b-button
          >
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";

export default {
  name: "favorite",
  data() {
    return {
      pizzaList: null,
      id: "",
      isEmpty: true,
    };
  },
  async mounted() {
    const res = await axios.get("user/getfavoritebyuser/" + this.id);

    this.pizzaList = res.data;

    if (this.pizzaList.length == 0) {
      this.isEmpty = false;
    }
  },

  created() {
    const res = sessionStorage.getItem("id");

    if (res != null) {
      this.id = res;
    }
  },

  methods: {

    /**
     * This method is used to add pizza item to the basket.
     * @param {pizzaID} id of pizza which is put to the basket.
     */
    async AddBasket(pizzaID) {
      await axios.post("user/addbasket", {
        userID: this.id,
        pizzaID: pizzaID,
      });

      this.makeBasketToast();
    },
    makeBasketToast(append = false) {
      this.toastCount++;
      this.$bvToast.toast("Added", {
        title: "Added",
        autoHideDelay: 1200,
        appendToast: append,
      });
    },

        /**
     * This method is used to remove pizza item to the basket.
     * @param {pizzaID} id of pizza which is removed to the basket.
     */

    async removeItem(pizzaID) {
      await axios.post("user/removefavoriteitem", {
        userID: this.id,
        pizzaID: pizzaID,
      });
      const res = await axios.get("user/getfavoritebyuser/" + this.id);

      this.pizzaList = res.data;

      if (this.pizzaList.length == 0) {
        this.isEmpty = false;
      }
    },
  },
};
</script>
