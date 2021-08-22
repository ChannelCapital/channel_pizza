<template>
  <div class="container">
    <header class="jumbotron">
      <table>
      <tr class="job">
        <td><a :href="'/pizza/' + content.pizzaId"><img :src="content.pizzaImageUrl" width="480"></a></td>
        <td>
          <h2>{{ content.pizzaName }} <span class="priceTag">{{ content.pizzaPrice }}</span></h2>
          <ul>
            <li>{{content.pizzaIngredients}}</li>
          </ul>
        </td>
      </tr>
    </table>
    </header>
  </div>
</template>

<script>
import UserService from "../services/user.service";

export default {
  props: ['id'],
  name: "Pizza Detail",
  data() {
    return {
      content: "",
      ingredients: []
    };
  },
  mounted() {
    UserService.getPizzaContent(this.id).then(
      (response) => {
        this.content = response.data;
      },
      (error) => {
        this.content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
      }
    );
  },
  methods: {
    addIngredients: function() {
      this.ingredients = this.content.ingredients.split(", ");
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
  font-size: 125%;
  color: red;
}
.priceTag:after {
  content: "$";
  vertical-align: super;
  font-size: 50%;
}
ul, h2 {
    margin: 0;
    padding: 0;
}
td {
    vertical-align: top;
}
</style>
