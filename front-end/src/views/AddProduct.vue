<template>
  <div class="submit-form">
    <br />
    <h4>Add a Product</h4>
    <div v-if="!submitted">
      <br />
      <div class="form-group">
        <label for="name">Name </label>
        <input
          type="text"
          class="form-control"
          id="name"
          required
          v-model="product.name"
          name="name"
        />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <input
          class="form-control"
          id="description"
          required
          v-model="product.description"
          name="description"
        />
      </div>
      <div class="form-group">
        <label for="imageURL">Image URL</label>
        <input
          type="text"
          class="form-control"
          id="imageURL"
          required
          v-model="product.imageURL"
          name="imageURL"
        />
      </div>
      <div class="form-group">
        <label for="price">Price</label>
        <input
          type="text"
          class="form-control"
          id="price"
          required
          v-model="product.price"
          name="price"
        />
      </div>
      <br />
      <button @click="saveProduct" class="btn btn-success" style="width: 40%">Submit</button>
    </div>
    <div v-else>
      <br />
      <br />
      <button class="btn btn-success" @click="newProduct">Add New Product</button>
    </div>
  </div>
</template>
<script>
import productService from "../services/product.service";
import swal from "sweetalert";

export default {
  name: "add-product",
  data() {
    return {
      product: {
        id: null,
        name: "",
        description: "",
        imageURL: "",
        price: 0,
        published: false
      },
      submitted: false
    };
  },
  methods: {
    saveProduct() {
      var data = {
        name: this.product.name,
        description: this.product.description,
        imageURL: this.product.imageURL,
        price: this.product.price
      };
      productService.create(data)
        .then(response => {
          this.product.id = response.data.id;
          swal({
            text: "Product added!",
            icon: "success",
          });
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newProduct() {
      this.submitted = false;
      this.product = {};
    }
  }
};
</script>
<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>