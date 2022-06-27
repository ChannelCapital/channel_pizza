
<template>
  <div v-if="currentAddress" class="edit-form">
    <h4>Address</h4>
    <form>
      <div class="form-group">
        <label for="country">Country:</label>
        <input type="text" class="form-control" id="country"
          v-model="currentAddress.country"
        />
      </div>
      <div class="form-group">
        <label for="city">City:</label>
        <input type="text" class="form-control" id="city"
          v-model="currentAddress.city"
        />
      </div>
      <div class="form-group">
        <label for="postcode">Postcode:</label>
        <input type="text" class="form-control" id="postcode"
          v-model="currentAddress.postcode"
        />
      </div>
    </form>
    <br />
    <button type="submit" class="btn btn-success"
      @click="updateAddress"
    >
      Submit
    </button>
  </div>
</template>
<script>
import userService from "../services/user.service";
//import cartService from "../services/cart.service";
import swal from "sweetalert";
export default {
  name: "update-address",
  data() {
    return {
      currentAddress: null,
    };
  },
  methods: {
    getAddress() {
      userService.getAdress()
        .then(response => {
          this.currentAddress = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    updateAddress() {
      userService.updateAdress(this.currentAddress.id, this.currentAddress)
        .then(response => {
          console.log(response.data);
          swal({
            text: "Address updated!",
            icon: "success",
          });
        })
        .catch(e => {
          console.log(e);
        });
        this.$router.go(-1);
    },
  },
  mounted() {
    this.getAddress();
    
    
  }
};
</script>
<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>