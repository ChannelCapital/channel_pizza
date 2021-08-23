<template>
  <div class="container">
    <header class="jumbotron">
      <div class="header">
        <!-- https://codepen.io/joshbivens/pen/NNpYYR -->
        <h2>Welcome to Channel Pizza</h2>
        <!-- <div>
          <button @click="showCart = !showCart" v-show="!verified">
            {{
              items.length +
              (items.length > 1 || items.length === 0 ? " items" : " item")
            }}
          </button>
        </div> -->
      </div>
      <div v-show="loggedIn === true">
        <div class="cart" v-show="showCart">
          <div v-show="items.length > 0">
            <table>
              <tr v-for="item in items" v-bind:key="item" transition="fade">
                <td>{{ item.quantity }}x</td><td>{{ item.pizzaName }}</td><td>${{ item.pizzaPrice * item.quantity }} <button @click="removeFromCart(item)">X</button></td>
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
      </div>
      <hr />
      <div class="container">
        <div class="shop" v-show="!verified">
          <ul>
            <li v-for="item in shop" :key="item">
              <div>
                <table>
                  <tr>
                    <td width="240px">
                      <img :src="item.pizzaImageUrl" width="240" />
                    </td>
                    <td>
                      <h5>{{ item.pizzaName }}</h5>
                      <ul>
                        <li
                          v-for="ins in splitJoin(item.pizzaIngredients)"
                          v-bind:key="ins"
                          v-text="ins"
                          class="tag"
                        ></li>
                      </ul>
                    </td>
                    <td width="10%" align="center">
                      <p><span class="priceTag">{{ item.pizzaPrice }}</span></p>
                      <button @click="addToCart(item)" v-show="loggedIn === true">Add to cart</button>
                      <button @click="$router.push('login')" v-show="loggedIn === false">Login to order</button>
                    </td>
                  </tr>
                </table>
              </div>
            </li>
          </ul>
        </div>
        <div class="checkout" v-show="verified">
          <h5 v-for="item in items" :key="item">
            <strong>{{ item.quantity }}</strong> - {{ item.pizzaName }}<span> ${{ item.pizzaPrice * item.quantity }}</span>
          </h5>
          <hr />
          <div class="row">
            <div class="u-pull-right" v-show="loggedIn === true">
              <h5>
                Total: <span>${{ total }}</span>
              </h5>
              <button>Looks Good</button> <button @click="(verified = false), (showCart = true)">Back</button>
            </div>
            <div class="u-pull-right" v-show="loggedIn === false">
              <h5>
                Total: <span>${{ total }}</span>
              </h5>
              <button @click="$router.push('login')">Log in</button>
            </div>
          </div>
        </div>
      </div>

      <!--       <table>
      <tr v-for="pizza in content" :key="pizza.pizzaId" class="job">
        <td><a :href="'/pizza/' + pizza.id"><img :src="pizza.pizzaImageUrl" width="240"></a></td>
        <td>
          <h2>{{ pizza.pizzaName }} <span class="priceTag">{{ pizza.pizzaPrice }}</span></h2>
          <ul>
            <li v-for="ins in splitJoin(pizza.pizzaIngredients)" v-bind:key="ins" v-text="ins"></li>
          </ul>
        </td>
      </tr>
    </table> -->
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
      items: [],
      showCart: true,
      verified: false,
      quantity: 1,
    };
  },
  mounted() {
    UserService.getPublicContent().then(
      (response) => {
        this.shop = response.data;
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
  font-size: 12px;
  display: inline;
  background-color: cadetblue;
  padding: 5px 5px;
  border-radius: 12px;
  color: white;
  text-transform: uppercase;
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
</style>
