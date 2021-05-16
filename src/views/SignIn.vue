<template>
  <b-container style="margin-top: 50px">
    <b-row>
      <b-col cols="4"></b-col>
      <b-col cols="4">
        <b-form>
          <b-form-group id="input-group-1">
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
            <p v-if="badLogin">Incorrect credentials</p>
          </b-form-group>
        </b-form>
        <div>
          <b-button block variant="primary" @click="submitted()"
            >Sign In</b-button
          >
        </div>
      </b-col>
      <b-col cols="4"></b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";

export default {
  name: "login",

  data() {
    return {
      email: "",
      password: "",
      badLogin: false,
    };
  },

  methods: {

    /**
      *This method enables to work login properties given user credentials.
      */
    async submitted() {
      const res = await axios.post("user/signin", {
        email: this.email,
        password: this.password,
      });

      if (res.data.id != null) {
        sessionStorage.setItem("id", res.data.id);
        sessionStorage.setItem("email", res.data.email);
        sessionStorage.setItem("address", res.data.address);
        this.$store.commit("setAuthentication", true);


        this.$router.push("/");
        location.reload();
      } else {
        this.badLogin = true;
      }
    },
  },
};
</script>
