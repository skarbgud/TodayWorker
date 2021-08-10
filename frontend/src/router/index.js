import Vue from "vue"
import VueRouter from "vue-router"
import FrameSection from "../views/frame/components/FrameSection";
import CareerBoard from '@/views/content/templates/career';
import ClubBoard from '@/views/content/templates/club';
import CoffeeBoard from '@/views/content/templates/coffee';
import CompanyBoard from '@/views/content/templates/company';
import DefaultBoard from '@/views/content/templates/default';
import FreeBoard from '@/views/content/templates/freeboard';
import HobbyBoard from '@/views/content/templates/hobby';
import HomorBoard from '@/views/content/templates/humor';
import InformationBoard from '@/views/content/templates/information';
import IssueBoard from '@/views/content/templates/issue';
import LiveBoard from '@/views/content/templates/live';
import PromotionBoard from '@/views/content/templates/promotion';
import ResturantBoard from '@/views/content/templates/restaurant';
import SecretBoard from '@/views/content/templates/secret';
import UsedBoard from '@/views/content/templates/used';

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [{
      path: "/",
      component: FrameSection
    },
    {
      path: "/career",
      component: CareerBoard
    },
    {
      path: "/club",
      component: ClubBoard
    },
    {
      path: "/coffee",
      component: CoffeeBoard,
    },
    {
      path: "/company",
      component: CompanyBoard,
    },
    {
      path: "/default",
      component: DefaultBoard,
    },
    {
      path: "/freeboard",
      component: FreeBoard,
    },
    {
      path: "/hobby",
      component: HobbyBoard,
    },
    {
      path: "/humor",
      component: HomorBoard,
    },
    {
      path: "/information",
      component: InformationBoard,
    },
    {
      path: "/issue",
      component: IssueBoard,
    },
    {
      path: "/live",
      component: LiveBoard,
    },
    {
      path: "/promotion",
      component: PromotionBoard,
    },
    {
      path: "/restaurant",
      component: ResturantBoard,
    },
    {
      path: "/secret",
      component: SecretBoard,
    },
    {
      path: "/used",
      component: UsedBoard,
    },
  ],
})

export default router