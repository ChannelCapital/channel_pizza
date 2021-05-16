<template>
  <b-container class="bv-example-row" style="margin-top: 50px">
    <h3 v-if="this.total == 0">Basket is Empty</h3>
    <h3 v-if="this.total != 0">My Basket</h3>
    <b-row cols="1">
      <b-col v-for="(pizza, index) in pizzaList" :key="index++">
        <b-card class="mb-2" :title="pizza.name">
          <b-row cols="1">
            <b-col
              >£{{ pizza.price }}
              <p variant="primary">
                <strong
                  style="cursor: pointer"
                  @click="removeItem(pizza.id, pizza.price)"
                  >Remove</strong
                >
              </p></b-col
            >
          </b-row>
        </b-card>
      </b-col>
      <b-col> </b-col>
    </b-row>
    <b-row cols="1">
      <b-col>
        <b-card class="mb-2" v-if="this.total != 0">
          <b-row cols="2">
            <b-col>
              <b-button @click="GiveOrder(id)" variant="primary"
                >Proceed</b-button
              >
            </b-col>

            <b-col>
              <strong>Total Price: £{{ total }}</strong>
            </b-col>
          </b-row>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";

export default {
  name: "basket",
  data() {
    return {
      id: "",
      pizzaList: null,
      total: 0,
      pizzaName: null,
      pizzaPrice: null,
      pizza: null,
      totalText: "",
    };
  },
  async mounted() {
    let res = await axios.get("user/getbasketbyuser/" + this.id);

    this.pizzaList = res.data;

    for (var i = 0; i < this.pizzaList.length; i++) {
      this.pizza = this.pizzaList[i];

      this.total = this.total + this.pizza.price;
    }

    this.total = Math.round(this.total * 100) / 100;
  },

  created() {
    const res = sessionStorage.getItem("id");

    if (res != null) {
      this.id = res;
    }
  },

  methods: {
    async GiveOrder(userID) {
      var orderList = [];

      for (var i = 0; i < this.pizzaList.length; i++) {
        this.pizza = this.pizzaList[i];

        orderList.push(this.pizza);
      }

      var obj = {
        userID: userID,
        pizzaID: orderList,
      };

      await axios.post("order/addorder", obj);

        this.$router.push("/order");
    },

    async removeItem(pizzaID, pizzaPrice) {
      var obj = {
        userID: this.id,
        pizzaID: pizzaID,
      };

      await axios.post("user/removebasketitem", obj);

      let res = await axios.get("user/getbasketbyuser/" + this.id);

      this.pizzaList = res.data;

              this.total = this.total - pizzaPrice;


      this.total = Math.round(this.total * 100) / 100;
    },
  },
};
</script>
