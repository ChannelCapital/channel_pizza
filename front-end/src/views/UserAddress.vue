/* eslint-disable vue/no-multiple-template-root */

<template>
  <div class="submit-form">
    <br />
    <h4>New Address</h4>
    <br />
    <div class="form-group">
      <label for="country">Country</label>
      <input
        type="text"
        class="form-control"
        id="country"
        required
        v-model="address.country"
        name="country"
      />
    </div>
    <div class="form-group">
      <label for="city">City</label>
      <input
        class="form-control"
        id="city"
        required
        v-model="address.city"
        name="city"
      />
    </div>

    <div class="form-group">
      <label for="postcode">Postcode</label>
      <input
        class="form-control"
        id="postcode"
        required
        v-model="address.postcode"
        name="postcode"
      />
    </div>
    

      <!--

      1) <input type="text" placeholder="Search..." class="form-control" 
      v-model="address.postcode" v-on:change="getSearchResults">

      <ul>
        <li v-for="item in searchResults" :key="item">
          <div class="text-center">
              <span>{{item}}</span>
          </div>
        </li>
      </ul>


      2) <autocomplete
      ref="autocomplete"
      :source="autoPostcode"
      input-class="form-control"
      name="postcode"
      results-property="postcodes"
      @selected="addPostcode"
      >

      3) :source="postcodes"
        color="white"
        item-value="postcode"
        item-text="Postcode"
        label="Postcode"
      </autocomplete>

      -->

    <br />
    <button @click="saveAddress" class="btn btn-success">Submit</button>
  </div>
</template>
<script>
import userService from "../services/user.service";
//import postcodeService from "../services/postcode.service";
import swal from "sweetalert";

export default {
  name: "add-address",
  data() {
    return {
      address: {
        id: null,
        country: "",
        city: "",
        postcode: "",
      },
    };
  },
  methods: {
    saveAddress() {
      var data = {
        country: this.address.country,
        city: this.address.city,
        postcode: this.address.postcode,
        userId: JSON.parse(localStorage.user).id,
      };
      userService
        .addAddress(data)
        .then((response) => {
          this.address.id = response.data.id;
          swal({
            text: "Address added!",
            icon: "success",
          });
          console.log(response.data);
          this.$router.go(0);
        })
        .catch((e) => {
          console.log(e);
        });

      this.$router.go(-1);
    },
    


/*
1)getSearchResults: function(){
    var app = this
      console.log(this.query)
      postcodeService.getPostcode(this.postcode)
      .then(function(res){
        console.log(app.searchResults)
          app.searchResults = res.data     
          }).catch(function(e){
            console.log("Error" + e)
          })
      },

      mounted() {
    this.getPostcode();
  },

2)autoPostcode(input) {
      axios
        .get(`api.postcodes.io/postcodes/${input}/autocomplete`)
        .then((response) => {
          this.$refs.autocomplete.$el = response.data;
        });
    },
    addPostcode(data) {
      this.data = data;
      // access the autocomplete component methods from the parent
      this.$refs.autocomplete.clear();
    },
    formattedDisplay(data) {
      return data;
    },
    */


   
  },
  
};
</script>
<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>