import Vue from "vue"
import VueRouter from "vue-router"
import FrameSection from "../views/frame/components/FrameSection";
import LiveBoard from '../views/live/LiveBoard';

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [{
      path: "/",
      component: FrameSection
    },
    {
      path: "/live",
      component: LiveBoard,
    },
  ],
})

export default router