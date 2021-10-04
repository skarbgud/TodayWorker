import Vue from 'vue'
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import { IconsPlugin } from 'bootstrap-vue'
import '@fortawesome/fontawesome-free/js/all.js'
// import { Swiper, SwiperSlide } from "vue-awesome-swiper";

import router from "./router"
import axios from 'axios';

Vue.use(IconsPlugin);
Vue.use(BootstrapVue);
Vue.use(ElementUI);
Vue.use(axios);
// Vue.use(Swiper);
// Vue.use(SwiperSlide);

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')