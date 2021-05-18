import Vue from 'vue';

// element-ui
import ElementUI from 'element-ui';
import locale from 'element-ui/lib/locale/lang/ko';

// dependencies styles
import 'normalize.css/normalize.css';
import '@fortawesome/fontawesome-free/css/all.css';

// custom styles
import './assets/scss/base.scss';

// components
import App from './App';

// router
import router from './router';

// store
import store from './store';

//axios
import axios from './api';

Vue.use(ElementUI, {
  size: 'mini',
  locale,
});

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: {
    App,
  },
  template: '<App/>',
  router,
  store,
  axios,
});
/* eslint-enable no-new */
