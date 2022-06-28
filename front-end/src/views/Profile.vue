<template>
  <div class="container">
    <br />
    <h3>
      <strong>{{ currentUser.username }}</strong
      >'s profile
    </h3>
    <br />
    <p>
      <strong>Token:</strong>
      {{ currentUser.accessToken.substring(0, 10) }} ...
      {{ currentUser.accessToken.substr(currentUser.accessToken.length - 10) }}
    </p>
    <p>
      <strong>Id:</strong>
      {{ currentUser.id }}
    </p>
    <p>
      <strong>Email:</strong>
      {{ currentUser.email }}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="(role, index) in currentUser.roles" :key="index">
        {{ role }}
      </li>
    </ul>
    <!--
    <div class="col-md-6">
      <p>
        <strong>ADDRESS INFO</strong>
      </p>
      <div v-if="currentAddress">
        <div>
          <label><strong>Country:</strong></label> {{ currentAddress.country }}
        </div>
        <br />
        <div>
          <label><strong>City:</strong></label> {{ currentAddress.city }}
        </div>
         <br />
        <div>
          <label><strong>Postcode:</strong></label>
          {{ currentAddress.postCode }}
        </div>
         <br />
      </div>
      <a v-if="!currentAddress" class="btn btn-secondary" :href="`#/address`">
          Add Address
        </a>

        <a v-if="currentAddress" class="btn btn-secondary" :href="`#/address/${currentAddress.id}`">
          Update
        </a>
    </div>
   -->
  </div>
</template>
<script>
import userService from "../services/user.service";

export default {
  name: "UserProfile",
  data() {
    return {
      currentAddress: null,
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  methods: {
    getAddress() {
      userService
        .getAdress()
        .then((response) => {
          this.currentAddress = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },

  mounted() {
    if (!this.currentUser) {
      this.$router.push("/login");
    }
    this.getAddress();
  },
};
</script>


