import '../node_modules/bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue } from 'bootstrap-vue'
import variables from './data/variables'

Vue.config.productionTip = false

Vue.use(BootstrapVue)

new Vue({
  globals: {
    variables
  },
  router,
  render: h => h(App),
}).$mount('#app')

import 'bootstrap/dist/js/bootstrap.bundle.js'