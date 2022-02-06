import Vue from "vue";
import VueRouter from "vue-router";
import FrameSection from "../views/frame/components/FrameSection";
import BoardList from "../views/content/board";
import BoardContent from "../views/content/board/BoardContent";
import EatContent from "../views/content/board/eat/EatContent";

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/eat",
      component: EatContent,
    },
    {
      path: "/",
      component: FrameSection,
    },
    {
      path: "/:board",
      component: BoardList,
    },
    {
      path: "/:board/:index",
      component: BoardContent,
    },
  ],
});

export default router;
