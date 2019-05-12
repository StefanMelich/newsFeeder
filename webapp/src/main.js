import Vue from 'vue'
import App from './App.vue'
import router from './router'

import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbvue/build/css/mdb.css';



Vue.config.productionTip = false

new Vue({
  router, // inject the router to make whole app router-aware
  render: h => h(App),
}).$mount('#app')
