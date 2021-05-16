<template>
  <b-container style="margin-top: 50px">
    <b-row>
      <b-col cols="4"> </b-col>
      <b-col cols="4">
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
              required
              v-model="password"
            ></b-form-input>
          </b-form-group>

          <b-form-group id="input-group-3" label-for="input-3">
            <b-form-input
              id="input-3"
              type="password"
              placeholder="Confirm password"
              v-model="cPassword"
              required
            ></b-form-input>
            <p v-if="!isMatch">Passwords do not match</p>
          </b-form-group>
        </b-form>

        <div>
          <b-button block variant="primary" @click="submitted()"
            >Sign Up</b-button
          >
        </div>
      </b-col>
      <b-col cols="4"> </b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";

export default {
  name: "signup",
  data() {
    return {
      email: "",
      password: "",
      cPassword: "",
      isMatch: true,
    };
  },



  methods: {

      /**
        *This method enables to save user credentials to the db
        */
    async submitted() {
      if (this.password != this.cPassword) {
        this.isMatch = false;
      } else {
        await axios.post("user/adduser", {
          email: this.email,
          password: this.password,
        });

        this.$router.push("/signin");
      }
    },
  },
};
</script>