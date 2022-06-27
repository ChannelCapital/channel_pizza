import Vue from 'vue';
import App from './App.vue';
import { router } from './router';
import Autocomplete from 'vuejs-auto-complete'
import store from './store';
import swal from 'sweetalert';
import 'bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import VeeValidate from 'vee-validate';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import {
  faHome,
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt
} from '@fortawesome/free-solid-svg-icons';
library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);
Vue.config.productionTip = false;
Vue.use(VeeValidate);

Vue.component('font-awesome-icon', FontAwesomeIcon);
// eslint-disable-next-line vue/multi-word-component-names
Vue.component('autocomplete', Autocomplete);
new Vue({
  router,
  store,
  swal,
  Autocomplete,
  render: h => h(App)
}).$mount('#app');