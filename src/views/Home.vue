<template>
  <b-container class="bv-example-row" style="margin-top: 10px">
    <b-row cols="3">
      <b-col v-for="pizza in pizzaList" :key="pizza.id">
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
          <b-card-text> £{{ pizza.price }} </b-card-text>
          <b-button
            @click="AddBasket(pizza.id)"
            style="margin: 10px"
            variant="primary"
            v-if="id.length > 0"
            >Add to Basket</b-button
          >
          <b-button
            @click="AddFavorites(pizza.id)"
            variant="primary"
            v-if="id.length > 0"
            >Add to Favorites</b-button
          >
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";

export default {
  name: "home",
  data() {
    return {
      pizzaList: null,
      id: "",
    };
  },
  async mounted() {
    const res = await axios.get("pizza/allpizza");

    this.pizzaList = res.data;
  },
  created() {
    this.$store.commit("setAuthentication", true);

    const res = sessionStorage.getItem("id");

    if (res != null) {
      this.id = res;
    }
  },

  methods: {

    /**
     * this method is used to add pizza item to the user favorites
     * @param {pizzaID} indicator of pizza
     */

    async AddFavorites(pizzaID) {
      await axios.post("user/addfavorite", {
        userID: this.id,
        pizzaID: pizzaID,
      });

      this.makeFavoriteToast();
    },
    makeFavoriteToast(append = false) {
      this.toastCount++;
      this.$bvToast.toast("Added", {
        title: "Added",
        autoHideDelay: 1200,
        appendToast: append,
      });
    },    makeBasketToast(append = false) {
      this.toastCount++;
      this.$bvToast.toast("Added", {
        title: "Added",
        autoHideDelay: 1200,
        appendToast: append,
      });
    },

        /**
     * this method is used to add pizza item to the user baskets
     * @param {pizzaID} indicator of pizza
     */

    async AddBasket(pizzaID) {
      await axios.post("user/addbasket", {
        userID: this.id,
        pizzaID: pizzaID,
      });
            this.makeBasketToast();

    },
  },
};
</script>
