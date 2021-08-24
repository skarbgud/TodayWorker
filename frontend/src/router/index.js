import Vue from "vue"
import VueRouter from "vue-router"
import FrameSection from "../views/frame/components/FrameSection";
import BoardList from '../views/content/board';
import BoardDetail from '../views/content/board/BoardDetail';
import cardTitle from '@/constant/index';

Vue.use(VueRouter);

const router = new VueRouter({

  mode: "history",
  routes: [{
      path: "/",
      component: FrameSection
    },
    {
      path: `/:${cardTitle.path}`,
      component: BoardList,
      name: BoardList,
    },
    {
      path: `/:${cardTitle.path}/:index`,
      component: BoardDetail,
      name: BoardDetail,
    }
  ],
})

export default router