<template>
  <div class="container">
    <header class="jumbotron">
      <div class="header">
        <!-- https://codepen.io/joshbivens/pen/NNpYYR -->
        <h2>Welcome to Channel Pizza</h2>
      </div>
      <div v-show="loggedIn === true">
        <div class="cart" v-show="showCart">
          <div v-show="items.length > 0">
            <table class="tableCart">
              <tr v-for="item in items" v-bind:key="item" transition="fade">
                <td :class="item.id">{{ item.quantity }}x</td><td>{{ item.pizzaName }}</td><td>${{ (item.pizzaPrice * item.quantity).toFixed(2) }} <button @click="removeFromCart(item)">X</button></td>
              </tr>
            </table>
            <div>
              <button @click="(verified = true), (showCart = false)" class="checkout-btn">
                Check out
              </button>
            </div>
          </div>
          <div v-show="items.length === 0">
            <p style="width:250px; text-align:left">Your cart is empty!</p>
          </div>
        </div>
        <div class="delivery" v-show="delivery">
          <div v-show="orders.length > 0">
            <table class="tableCart">
              <tr>
                <td align="left" colspan="2">Your order is...</td>
              </tr>
              <tr v-for="order in orders" v-bind:key="order" transition="fade" style="border: 1px solid">
                <td v-show="order.deliveryStatus === 0" width="120px">
                    <p class="orderStatus">...being prepared.</p>
                </td>
                <td v-show="order.deliveryStatus === 1">
                    <p class="orderStatus">...on its way.</p>
                </td>
                <!-- <td :class="order.id">{{ order.id }}</td> -->
                <td v-show="order.deliveryStatus < 2">
                  <div v-for="pizzaa in order.orderItem" v-bind:key="pizzaa" transition="fade">
                    <ul>
                      <li class="orderStatus">{{ pizzaa.quantity }}x {{ pizzaa.pizzaName }}</li>
                    </ul>
                  </div>
                </td>
              </tr>
            </table>            
          </div>
        </div>
      </div>
      <hr />
      <div class="container">
        <div class="shop" v-show="!verified">

          <!-- <input type="text" v-model="ingredient" placeholder="Filter by ingredient" @input="filterByIngredient()"/> -->

          <ul style="display: flex; flex-wrap: wrap; justify-content: space-between;">
            <li v-for="item in shop" :key="item" style="width: 300px">
              <div>
                <table>
                  <tr>
                    <td width="240px" colspan="2">
                      <div class="imageTd">
                        <img :src="item.pizzaImageUrl" width="240" />
                      </div>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="2">
                      <h5>{{ item.pizzaName }} {{ item.msg }}</h5>
                    </td>
                  </tr>
                  <tr style="height: 150px">
                    <td>
                      <ul>
                        <li
                          v-for="ins in splitJoin(item.pizzaIngredients)"
                          v-bind:key="ins"
                          v-text="ins"
                          class="tag"
                        ></li>
                      </ul>
                    </td>
                    <td align="center">
                      <p><span class="priceTag">{{ item.pizzaPrice.toFixed(2) }}</span></p>
                      <button @click="addToCart(item)" v-show="loggedIn === true" :class="'pizzaIndex'+ item.index">Add to cart</button>
                      <button @click="$router.push('login')" v-show="loggedIn === false" class="loginToOrder">Login to order</button>
                    </td>
                  </tr>
                </table>
              </div>
            </li>
          </ul>
        </div>
        <div class="checkout" v-show="verified">
          <div v-show="!ordered">
            <h5 v-for="item in items" :key="item">
              <strong>{{ item.quantity }}</strong> x {{ item.pizzaName }}<span> = ${{ (item.pizzaPrice * item.quantity).toFixed(2) }}</span>
            </h5>
            <hr />
            <div class="row">
              <div class="u-pull-right" v-show="loggedIn === true">
                <h5>
                  Total: <span class="totalCost">${{ total.toFixed(2) }}</span>
                </h5>
                <button @click="placeOrder(items)" class="placeOrder">Looks Good</button> <button @click="(verified = false), (showCart = true)">Back</button>
              </div>
              <div class="u-pull-right" v-show="loggedIn === false">
                <h5>
                  Total: <span>${{ total.toFixed(2) }}</span>
                </h5>
                <button @click="$router.push('login')">Log in</button>
              </div>
            </div>
          </div>
          <div v-show="ordered">
            <div class="row">
              <div class="u-pull-right" v-show="loggedIn === true">
                <h5 class="confirmation">
                  We have received your order. It should be on its way to you within 30 minutes.
                </h5>
                <button @click="reloadPage" class="returnHome">Back</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
  </div>
</template>

<script>
import UserService from "../services/user.service";

export default {
  name: "Home",
  data() {
    return {
      shop: "",
      orders: "",
      items: [],
      showCart: true,
      verified: false,
      ordered: false,
      delivery: true
    };
  },
  mounted() {
    UserService.getOpenOrders(this.currentUser.id).then(
      (response) => {
        this.orders = response.data;
        console.log(this.orders)
      },
      (error) => {
        this.orders =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
      },
    );
    UserService.getPublicContent().then(
      (response) => {
        this.shop = response.data;

        for (let index = 0; index < this.shop.length; index++) {
          this.shop[index]['quantity'] = 0;
          this.shop[index]['index'] = index + 1;
        }
      },
      (error) => {
        this.shop =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    total() {
      var total = 0;
      for (var i = 0; i < this.items.length; i++) {
        total += this.items[i].pizzaPrice * this.items[i].quantity; // won't add up price of multiple instances of same pizza
      }
      return total;
    },
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  methods: {
    placeOrder(items) {
      items['deliveryStatus']=0;
      UserService.postOrder(items, this.currentUser.id);  // submit order
      this.ordered = true;
      for (let index = 0; index < this.items.length; index++) {
        this.items[index]['quantity'] = 0;
      }
      this.items.splice(0)
      this.$router.push("/home");
    },
    splitJoin(theText) {
      return theText.split(", ");
    },
    addToCart(item) {
      const indexItem = this.items.findIndex(x=>x.id === item.id);
      if(indexItem >= 0){
        item.quantity += 1;
        // this.items.push(item); // causes count error when added or removed
      }else{
        item.quantity += 1;
        this.items.push(item);
      }
    },
    addClick(item) { // https://stackoverflow.com/questions/48404072/how-to-remove-the-duplicate-list-with-vue
      this.showcart = false;
      const indexItem = this.items.findIndex(x=>x.id === item.id);
      if(indexItem >= 0){
        this.items[indexItem].quantity += 1;
      }else{
        item.quantity += 1;
        this.items.push(item);
      }
    },
    removeFromCart(item) {
      const indexItem = this.items.slice(x=>x.id === item.id);
      if(indexItem >= 0){
        this.items[indexItem].quantity = 1;
      } else {
        item.quantity = 0;
        this.items.splice(this.items.indexOf(item), 1);
      }
    },
    reloadPage() {
      window.location.reload();
    }
  },
};
</script>


<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  /* display: inline-block; */
  margin: 0 10px;
}
a {
  color: #42b983;
}
.priceTag {
  font-size: 200%;
  font-weight: bold;
}
.priceTag:before {
  content: "$";
  vertical-align: super;
  font-size: 50%;
}
ul,
h2 {
  margin: 0;
  padding: 0;
}
td {
  vertical-align: top;
}
.tag {
  font-size: 10px;
  background-color: cadetblue;
  padding: 5px 5px;
  border-radius: 12px;
  color: white;
  text-transform: uppercase;
  margin: 5px 0;
}

.header {
  height: 80px;
}
.header h2 {
  color: #000;
  font-family: 'Roboto Slab', serif;
  float: left;
  font-weight: bolder;
  margin: 10px 20px;
}
.header button {
  border: 0;
  background: #ff0062;
  color: white;
  transition: all 0.1s ease-out;
}
.header button :hover {
  background: darken(#ffdbe0, 2%);
}

.header div {
  float: right;
  display: inline;
  margin: 10px;
}

.fade-transition {
  transition: all 0.2s ease-out;
}

.fade-enter,
.fade-leave {
  opacity: 0;
}

.cart > div {
  z-index: 100;
  background: #fff;
  padding: 20px 30px;
  position: absolute;
  left: 30px;
  box-shadow: 2px 2px 6px 0 rgba(0, 0, 0, 0.3);
}
.cart table {
  width: 250px;
}
.cart table td:nth-child(2) {
  text-align: left;
  width: 150px;
}
.cart table td:nth-child(3) {
  text-align: right;
  width: 100px;
}

.cart div {
  text-align: right;
}
.cart ul,
li,
p {
  margin-bottom: 0;
  max-width: max-content;
}
.cart button {
  /* margin: 20px 0 10px;*/
  text-transform: uppercase;
  text-decoration: none;
  font-weight: bold;
  letter-spacing: 2px;
}
.cart input {
  width: 30px;
}

.shop h3 {
  position: absolute;
  top: -85px;
  left: -30px;
  color: rgba(255, 255, 255, 0.3);
  font-family: sans-serif;
  font-size: 4em;
  font-weight: bold;
  letter-spacing: -2px;
}
.shop li div {
  padding: 30px;
  background: #fff;
  margin-bottom: 20px;
  box-shadow: 1px 1px 6px 0 rgba(0, 0, 0, 0.3);
}
.shop button {
  color: white;
  border: 0;
  background: #00adb5;
  margin: 0;
  transition: all 0.1s ease-out;
}
.shop button:hover {
  background: darken(#00adb5, 5%);
}

.checkout {
  background: #fff;
  box-shadow: 1px 1px 6px 0 rgba(0, 0, 0, 0.3);
  margin-top: 90px;
  padding: 50px 60px;}

.checkout h3 {
    position: absolute;
    top: -85px;
    left: -30px;
    color: rgba(255, 255, 255, 0.3);
    font-family: sans-serif;
    font-size: 4em;
    font-weight: bold;
    letter-spacing: -2px;
  }


.delivery > div {
  z-index: 100;
  background: #fff;
  padding: 20px 30px;
  position: absolute;
  right: 30px;
  box-shadow: 2px 2px 6px 0 rgba(0, 0, 0, 0.3);
}
.delivery table {
  width: 250px;
}

.delivery div {
  text-align: right;
}
.delivery ul {
  margin-bottom: 0;
}
.delivery button {
  /* margin: 20px 0 10px;*/
  text-transform: uppercase;
  text-decoration: none;
  font-weight: bold;
  letter-spacing: 2px;
}
.delivery input {
  width: 30px;
}

.orderStatus {
  font-size: 11px;
}
.imageTd {
    padding: 0 !important;
    height: 150px;
    overflow: hidden;
}
button[class^="pizzaIndex"] {
  padding: 10px 5px;
}
</style>
