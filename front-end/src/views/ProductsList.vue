<template>
  <div class="list row">
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="Search by name"
          v-model="name"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="searchName"
          >
            Search
          </button>
        </div>
    </div>
    <div class="col-md-6">
      <h4>Products List</h4>
      <ul class="list-group" >
        <li
          class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(product, index) in products"
          :key="index"
          @click="setActiveProduct(product, index)"
        >
          {{ product.name }}
        </li>
      </ul>
      <div style="text-align: center">
        <br />
      <button class="m-6 btn btn-md btn-danger" style="width: 35%" @click="removeAllProducts">
        Remove All
      </button>
      </div>
       <br />
    <br />
    </div>
   

    <div class="col-md-6">
      <h4>Product</h4>
      <div v-if="currentProduct">
        <ul class="list-group">
          <li class="list-group-item">
            <strong>Name:</strong> {{ currentProduct.name }}
          </li>
          <li class="list-group-item">
            <strong>Description:</strong> {{ currentProduct.description }}
          </li>
          <li class="list-group-item">
            <strong>imageURL:</strong> {{ currentProduct.imageURL }}
          </li>
          <li class="list-group-item">
            <strong>Price:</strong> {{ currentProduct.price }}
          </li>
          <li class="list-group-item">
            <strong>Status:</strong>
            {{ currentProduct.published ? "Published" : "Pending" }}
          </li>
        </ul>
        <div style="text-align: center">
        <br />
        <button class="m-6 btn btn-md btn-danger" style="width: 35%">
          <a :href="`#/details/${currentProduct.id}`" style="color: white; text-decoration:none"> Edit </a>
        </button>
        <br /><br />
        <br /><br />
        </div>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Product...</p>
        <br /><br />
        <br /><br />
      </div>
    </div>

    <!--
    <div class="col-md-6 ">
      <div v-if="currentProduct" class="list-group">
        <h4>Product</h4>
        <div>
          <label><strong>Name:</strong></label> {{ currentProduct.name }}
        </div>
        <div>
          <label><strong>Description:</strong></label>
          {{ currentProduct.description }}
        </div>
        <div>
          <label><strong>imageURL:</strong></label>
          {{ currentProduct.imageURL }}
        </div>
        <div>
          <label><strong>Price:</strong></label> {{ currentProduct.price }}
        </div>
        <div>
          <label><strong>Status:</strong></label>
          {{ currentProduct.published ? "Published" : "Pending" }}
        </div>

        <a class="badge badge-warning list-group" :href="`#/details/${currentProduct.id}`">
          Edit
        </a>
      </div>
      <div v-else>
        <br />
        <p>Please click on a Product...</p>
      </div>
    </div>



  text-align: center;
    -->
  </div>
</template>
<script>
import productService from "../services/product.service";
import swal from "sweetalert";

export default {
  name: "products-list",
  data() {
    return {
      products: [],
      currentProduct: null,
      currentIndex: -1,
      name: "",
    };
  },
  methods: {
    retrieveProducts() {
      productService
        .getProductsList()
        .then((response) => {
          this.products = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
    refreshList() {
      this.retrieveProducts();
      this.currentProduct = null;
      this.currentIndex = -1;
    },
    setActiveProduct(product, index) {
      this.currentProduct = product;
      this.currentIndex = index;
    },
    removeAllProducts() {
      productService
        .deleteAll()
        .then((response) => {
          swal({
            text: "All products deleted!",
            icon: "info",
          });
          console.log(response.data);
          this.refreshList();
        })
        .catch((e) => {
          console.log(e);
        });
    },

    searchName() {
      productService
        .findByName(this.name)
        .then((response) => {
          this.products = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
  mounted() {
    this.retrieveProducts();
  },
};
</script>
<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
.badge {
  background-color: crimson;
}
.col-md-6 {
  width: 100%;
  border-collapse: collapse;
}

.list-group-item {
  border: 1px solid #dddddd;
  padding: 8px;
}
.m-6 btn btn-md btn-danger{
  width: 50%;
}
</style>
