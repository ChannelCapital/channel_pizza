<template>
  <div v-if="currentProduct" class="edit-form">
    <br />
    <h4>Product</h4>
    <form>
      <br />
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name"
          v-model="currentProduct.name"
        />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <input type="text" class="form-control" id="description"
          v-model="currentProduct.description"
        />
      </div>
      <div class="form-group">
        <label for="imageURL">Image URL</label>
        <input type="text" class="form-control" id="imageURL"
          v-model="currentProduct.imageURL"
        />
      </div>
      <div class="form-group">
        <label for="price">Price</label>
        <input type="text" class="form-control" id="price"
          v-model="currentProduct.price"
        />
      </div>
      <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentProduct.published ? "Published" : "Pending" }}
      </div>
    </form>
    <button class="m-6 btn btn-md btn-danger" style="width: 33%; margin-right: 10px"
      v-if="currentProduct.published"
      @click="updatePublished(false)"
    >
      UnPublish
    </button>
    <button v-else class="m-6 btn btn-md btn-danger" style="width: 33%; margin-right: 10px"
      @click="updatePublished(true)"
    >
      Publish
    </button>
    <button class="m-6 btn btn-md btn-danger" style="width:25%; margin-right: 10px"
      @click="deleteProduct"
    >
      Delete
    </button>
    <button type="submit" class="m-6 btn btn-md btn-danger" style="width: 30%"
      @click="updateProduct"
    >
      Update
    </button>
  </div>
  <div v-else>
    <br />
    <p>Please click on a Product...</p>
  </div>
</template>
<script>
import productService from "../services/product.service";
import swal from "sweetalert";
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "details",
  data() {
    return {
      currentProduct: null,
    };
  },
  methods: {
    getProduct(id) {
      productService.getProductDetails(id)
        .then(response => {
          this.currentProduct = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    updatePublished(status) {
      var data = {
        id: this.currentProduct.id,
        name: this.currentProduct.name,
        description: this.currentProduct.description,
        imageURL: this.currentProduct.imageURL,
        price: this.currentProduct.price,
        published: status
      };
      productService.update(this.currentProduct.id, data)
        .then(response => {
          this.currentProduct.published = status;
          swal({
            text: "Product updated!",
            icon: "info",
          });
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    updateProduct() {
      productService.update(this.currentProduct.id, this.currentProduct)
        .then(response => {
          swal({
            text: "Product updated!",
            icon: "info",
          });
          console.log(response.data);
          
        })
        .catch(e => {
          console.log(e);
        });
    },
    deleteProduct() {
      productService.delete(this.currentProduct.id)
        .then(response => {
          swal({
            text: "Product deleted!",
            icon: "warning",
          });
          console.log(response.data);
          this.$router.push({ name: "products" });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getProduct(this.$route.params.id);
  }
};
</script>
<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>