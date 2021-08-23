<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
        class="profile-img-card"
      />
      <Form @submit="handleRegister" :validation-schema="schema">
        <div v-if="!successful">
          <div class="form-group">
            <label for="username">Username</label>
            <Field name="username" type="text" class="form-control" />
            <ErrorMessage name="username" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <Field name="email" type="email" class="form-control" />
            <ErrorMessage name="email" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="firstName">First name</label>
            <Field name="firstName" type="text" class="form-control" />
            <ErrorMessage name="firstName" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="lastName">Last name</label>
            <Field name="lastName" type="text" class="form-control" />
            <ErrorMessage name="lastName" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="postCode">Postal code</label>
            <Field v-model="code" name="postCode" type="text" class="form-control postcode" @input="postCodeSearch()" />
            <ErrorMessage name="postCode" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <Field name="password" type="password" class="form-control" />
            <ErrorMessage name="password" class="error-feedback" />
          </div>
          <div>
            <table class="table table-striped postcode">
              <tbody>
                <tr v-if="postcode.country">
                  <th scope="row">Country</th>
                  <td>{{ postcode.country }}</td>
                </tr>

                <tr v-if="postcode.nhs_ha">
                  <th scope="row">NHS Trust</th>
                  <td>{{ postcode.nhs_ha }}</td>
                </tr>

                <tr v-if="postcode.parliamentary_constituency">
                  <th scope="row">Parliament Constituency</th>
                  <td>{{ postcode.parliamentary_constituency }}</td>
                </tr>

                <tr v-if="postcode.admin_district">
                  <th scope="row">District</th>
                  <td>{{ postcode.admin_district }}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="form-group">
            <button class="btn btn-primary btn-block" :disabled="!postcode.admin_district">
            <!-- <button class="btn btn-primary btn-block"> -->
              <span
                v-show="loading"
                class="spinner-border spinner-border-sm"
              ></span>
              Register
            </button>
          </div>
        </div>
      </Form>

      <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";
import axios from "axios"

export default {
  name: "Register",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      username: yup
        .string()
        .required("Username is required!")
        .min(3, "Must be at least 3 characters!")
        .max(20, "Must be maximum 20 characters!"),
      email: yup
        .string()
        .required("Email is required!")
        .email("Email is invalid!")
        .max(50, "Must be maximum 50 characters!"),
      firstName: yup
        .string()
        .required("First name is required!")
        .min(2, "Must be at least 3 characters!")
        .max(20, "Must be maximum 20 characters!"),
      lastName: yup
        .string()
        .required("Last name is required!")
        .min(2, "Must be at least 3 characters!")
        .max(20, "Must be maximum 20 characters!"),
      postCode: yup
        .string()
        .required("Postal code is required!")
        .min(3, "Must be at least 3 characters!")
        .max(8, "Must be maximum 8 characters!"),
      password: yup
        .string()
        .required("Password is required!")
        .min(6, "Must be at least 6 characters!")
        .max(40, "Must be maximum 40 characters!"),
    });

    return {
      postcode: [],
      code: [],
      posterror: 1,
      successful: false,
      loading: false,
      message: "",
      schema,
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push("/home");
    }
  },
  methods: {
    postCodeSearch: function () {
      axios.get('http://localhost:8000/postcodes/' + this.code).then(response => {
        this.postcode = response.data.result;
        this.posterror=0;
        console.log(this.posterror)
      }).catch(function (error) {
        console.log(error.toJSON());
      })
    },
    handleRegister(user) {
      this.message = "";
      this.successful = false;
      this.loading = true;

      this.$store.dispatch("auth/register", user).then(
        (data) => {
          this.message = data.message;
          this.successful = true;
          this.loading = false;
        },
        (error) => {
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          this.successful = false;
          this.loading = false;
        }
      );
    },
  },
};
</script>

<style scoped>
.postcode {
  text-transform: uppercase;
}
label {
  display: block;
  margin-top: 10px;
}

.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}

.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}

.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}

.error-feedback {
  color: red;
}
</style>
