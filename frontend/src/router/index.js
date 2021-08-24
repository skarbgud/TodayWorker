import Vue from "vue"
import VueRouter from "vue-router"
import FrameSection from "../views/frame/components/FrameSection";
import BoardList from '../views/content/board';
import BoardContent from '../views/content/board/BoardContent';
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
      component: BoardContent,
      name: BoardContent,
    }
  ],
})

export default router