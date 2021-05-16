<template>
  <b-container fluid="sm" style="margin-top: 100px">
    <b-row align-v="center">
      <b-col sm="3"></b-col>
      <b-col sm="6">
        <b-form>
          <b-form-group id="input-group-1" label-for="input-1">
            <b-form-input
              id="input-1"
              type="email"
              placeholder="Email"
              required
              v-model="email"
            ></b-form-input>
          </b-form-group>

          <b-form-group id="input-group-2" label-for="input-2">
            <b-form-input
              id="input-2"
              type="password"
              placeholder="Password"
              v-model="password"
              required
            ></b-form-input>
          </b-form-group>

          <b-form-group id="input-group-3" label-for="input-3">
            <b-form-input
              id="input-3"
              type="password"
              placeholder="Re-enter password"
              v-model="rePassword"
              required
            ></b-form-input>
            <p v-if="!isMatch">Passwords do not match</p>
          </b-form-group>

          <b-form-group id="input-group-4" label-for="input-4">
            <b-form-input
              autocomplete="off"
              id="input-4"
              placeholder="Post Code"
              v-model="zipCode"
              required
              @keyup="getCode()"
            ></b-form-input>
            <b-list-group v-for="(zip, i) in zipList" :key="i++">
              <b-list-group-item v-if="!isSelected" @click="getItem(zip)">{{
                zip
              }}</b-list-group-item>
            </b-list-group>
          </b-form-group>

          <b-button @click="update()" block variant="primary">Send</b-button>
        </b-form>
      </b-col>
      <b-col sm="3"></b-col>
    </b-row>
  </b-container>
</template>

<script>
// @ is an alias to /src
import axios from "axios";

export default {
  name: "profile",
  data() {
    return {
      id: "",
      email: "",
      password: "",
      rePassword: "",
      zipCode: "",
      zipList: "",
      isSelected: false,
      isMatch: true,
    };
  },
  components: {},
  created() {
    const resId = sessionStorage.getItem("id");
    const resEmail = sessionStorage.getItem("email");
    const resAddress = sessionStorage.getItem("address");

    if (resId != null) {
      this.id = resId;
      this.email = resEmail;

      if (resAddress != "null") {
        this.zipCode = resAddress;
      } else {
        this.zipCode = "";
      }
    }
  },
  methods: {

    /**
     * this method update user informations
     * 
     * 
     */

    async update() {
      if (this.password != this.rePassword) {
        this.isMatch = false;
      } else {
        await axios.post("user/updateuser", {
          id: this.id,
          email: this.email,
          password: this.password,
          address: this.zipCode,
        });

        const newMail = sessionStorage.setItem("email", this.email);
        const newZip = sessionStorage.setItem("address", this.zipCode);

        this.email = newMail;
        this.zipCode = newZip;

      }

          location.reload();


    },

    /**
     * 
     * this method fetch postcode data from remote api for showing codes to the users.
     * 
     */

    async getCode() {
      if (this.zipCode != null && this.zipCode != "") {
        const list = await axios.get(
          "https://api.postcodes.io/postcodes/" + this.zipCode + "/autocomplete"
        );

        this.zipList = list.data.result;
        this.isSelected = false;
      }
    },
    getItem(zip) {
      this.zipCode = zip;
      this.isSelected = true;
    },
  },
};
</script>