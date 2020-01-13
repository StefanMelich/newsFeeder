import Vue from 'vue';
import App from './App.vue';
import router from './router';


import store from './auth/store';
import VeeValidate from 'vee-validate';

import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbvue/build/css/mdb.css';



Vue.config.productionTip = false

Vue.use(VeeValidate);


new Vue({
  router, // inject the router to make whole app router-aware
  store,
  render: h => h(App),
}).$mount('#app')
