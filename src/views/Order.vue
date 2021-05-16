<template>
  <b-container style="margin-top: 50px">
    <h3 v-if="!isEmpty">There are no orders</h3>
    <h3 v-if="isEmpty">My Orders</h3>
    <b-row>
      <b-col cols="2"></b-col>
      <b-col cols="8">
        <div>
          <div v-for="(order, index) in orderList" :key="index++">
            <b-card>
              <p v-b-toggle="'accordion-x' + order.id" class="card-text">
                <b-row>
                  <b-col cols="7"> Order ID: {{ order.id }} </b-col>

                  <b-col cols="5"> Total Price: £{{ order.orderTotal }} </b-col>
                </b-row>
                <br />
                <b-row>
                  <b-col cols="7">
                    Order Date: {{ order.date.substring(0, 10) }}
                  </b-col>

                  <b-col cols="5"></b-col>
                </b-row>
              </p>
            </b-card>
            <b-collapse :id="'accordion-x' + order.id" class="mt-2">
              <b-card>
                <p
                  v-for="(item, index) in order.pizzaID"
                  :key="index++"
                  class="card-text"
                >
                  <b-row>
                    <b-col cols="4"> {{ item.name }} </b-col>

                    <b-col cols="8">£{{ item.price }}</b-col>
                  </b-row>
                </p>
              </b-card>
            </b-collapse>
            <br />
          </div>
        </div>
      </b-col>
      <b-col cols="2"></b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";

export default {
  name: "order",
  data() {
    return {
      orderList: null,
      id: "",
      total: 0,
      isEmpty: true,
    };
  },

  async mounted() {
    const res = await axios.get("order/orderbyid/" + this.id);

    this.orderList = res.data;

    if (this.orderList.length == 0) {
      this.isEmpty = false;
    }
  },

  created() {
    const res = sessionStorage.getItem("id");

    if (res != null) {
      this.id = res;
    }
  },
};
</script>