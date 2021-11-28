import Vue from 'vue'
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import { IconsPlugin } from 'bootstrap-vue'
import '@fortawesome/fontawesome-free/js/all.js'
import InfiniteLoading from 'vue-infinite-loading';
import vueMoment from 'vue-moment'
import moment from 'moment'
import store from './store'

// import { Swiper, SwiperSlide } from "vue-awesome-swiper";

import router from "./router"
import axios from 'axios';

Vue.use(IconsPlugin);
Vue.use(BootstrapVue);
Vue.use(ElementUI);
Vue.use(axios);
Vue.use(InfiniteLoading);
moment.locale("ko");
Vue.use(vueMoment, { moment });
// Vue.use(Swiper);
// Vue.use(SwiperSlide);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')