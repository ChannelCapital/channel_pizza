<template>
  <div class="container">
    <header class="jumbotron">
      <carousel />
      <div class="container">
      <div class="row">
        <div class="col-12 text-center">
          <h4 class="pt-3" style="font-family: 'BIZ UDGothic', sans-serif;">SICILIAN CRUST PIZZA - 32cm</h4>
        </div>
      </div>
      <div class="row">
        <div v-for="product of products" :key="product.id"
          class="col-md-6 col-xl-4 col-12 pt-3 justify-content-around d-flex"
        >
          <ProductBox :product="product"> </ProductBox>
        </div>
      </div>
    </div>
    </header>
  </div>
</template>
<script>
import ProductService from '../services/product.service';
import carousel from '../components/CarouselSlide.vue'
import ProductBox from '../components/ProductBox.vue';

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Home',
  components: {
       
       carousel,
       ProductBox
       
   },
  data() {
    return {
      products: null,
    }
  },
  mounted() {
    ProductService.getProductsPublished().then(
      response => {
        this.products = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>
