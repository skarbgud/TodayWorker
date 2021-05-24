import Vue from 'vue';
import VueRouter from 'vue-router';
import MainFrame from '@/views/main/MainFrame';
import LiveBoard from '@/views/live/LiveBoard';

Vue.use(VueRouter);

export default new VueRouter({
  routes: [
    {
      path: '/',
      name: 'MainFrame',
      component: MainFrame,
    },
    {
      path: '/live',
      name: 'LiveBoard',
      component: LiveBoard,
    }
  ],
});
