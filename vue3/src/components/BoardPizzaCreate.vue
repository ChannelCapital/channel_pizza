<template>
  <div class="col-md-12">
    <div class="card card-container">
      <img
        id="profile-img"
        src="../assets/pizzaAvatar.jpeg"
        class="profile-img-card"
      />
      <Form @submit="createPizza" :validation-schema="schema" ref="form">
        <div v-if="!successful">
          <div class="form-group">
            <label for="pizzaName">Pizza name</label>
            <Field name="pizzaName" type="text" class="form-control" v-model="pizzaForm.pizzaFName"/>
            <ErrorMessage name="pizzaName" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="pizzaIngredients">Pizza ingredients</label>
            <Field name="pizzaIngredients" type="text" class="form-control" v-model="pizzaForm.pizzaFIn"/>
            <ErrorMessage name="pizzaIngredients" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="pizzaPrice">Pizza price</label>
            <Field name="pizzaPrice" type="text" class="form-control" v-model="pizzaForm.pizzaFPrice"/>
            <ErrorMessage name="pizzaPrice" class="error-feedback" />
          </div>
          <div class="form-group">
            <label for="pizzaImageUrl">Pizza image</label>
            <Field name="pizzaImageUrl" type="text" class="form-control" v-model="pizzaForm.pizzaFUrl"/>
            <ErrorMessage name="pizzaImageUrl" class="error-feedback" />
          </div>

          <div class="form-group">
            <button class="btn btn-primary btn-block" :disabled="loading">
              <span
                v-show="loading"
                class="spinner-border spinner-border-sm"
              ></span>
              Create Pizza
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
import UserService from "../services/user.service";
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";

export default {
  name: "Create Pizza",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      pizzaName: yup
        .string()
        .required("Pizza name is required!")
        .min(3, "Must be at least 3 characters!")
        .max(20, "Must be maximum 20 characters!"),
      pizzaPrice: yup
        .number()
        .required("Price is required!")
        .min(1, "Price must be at least 1$!")
        .max(20, "Price can not exceed 20$!")
        .test("maxDigitsAfterDecimal", "Price can have 2 digits after decimal or less",(pizzaPrice) => /^\d+(\.\d{1,2})?$/.test(pizzaPrice)),
      pizzaIngredients: yup
        .string()
        .required("Ingredients are required!")
        .min(4,"Ingredients are invalid!")
        .max(50, "Must be maximum 50 characters!"),
      pizzaImageUrl: yup
        .string()
        .required("Url to is required!")
        .min(10, "Must be at least 1 characters!")
        .max(512, "Must be maximum 2 characters!")
        .test("Check URL",
          (pizzaImageUrl) => {
            if (pizzaImageUrl) {
              return pizzaImageUrl.startsWith("https://") || pizzaImageUrl.startsWith("http://") ? true : false;
            }
          }

        ),
    });

    return {
      successful: false,
      loading: false,
      message: "",
      pizzaForm: {
        pizzaFName: "",
        pizzaFPrice: "",
        pizzaFIn: "",
        pizzaFUrl: "",
      },
      schema,
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  methods: {
    createPizza(pizza) {
      UserService.createPizza(pizza);
      console.log(pizza);
      this.pizzaForm.pizzaFName = "",
      this.pizzaForm.pizzaFPrice = "",
      this.pizzaForm.pizzaFIn = "",
      this.pizzaForm.pizzaFUrl = ""
    },
  },
};
</script>

<style scoped>
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
