import Vue from 'vue';
import VueRouter from 'vue-router';
import MainFrame from '@/views/main/MainFrame';

Vue.use(VueRouter);

export default new VueRouter({
  routes: [
    {
      path: '/',
      name: 'MainFrame',
      component: MainFrame,
    },
  ],
});
