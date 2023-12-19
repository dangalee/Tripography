import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "../views/TheMainView.vue";
// import TheBoardView from "../views/TheBoardView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: TheMainView,
    },
    {
      path: "/mypage",
      name: "mypage",
      component: () => import("../views/TheMyPageView.vue"),
      redirect: {name: "mypage-profile"},
      children: [
        {
          path: "profile",
          name: "mypage-profile",
          component: () => import("@/components/mypage/ProfileModify.vue"),
        },
        {
          path: "favorite",
          name: "mypage-favorite",
          component: () => import("@/components/mypage/FavoriteModify.vue"),
        },
        {
          path: "photo",
          name: "mypage-photo",
          component: () => import("@/components/mypage/PhotoModify.vue"),
        },
      ]
    },
    {
      path: "/board",
      name: "board",
      // component: TheBoardView,
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import("../views/TheBoardView.vue"),
      redirect: { name: "article-list" },
      children: [
        {
          path: "list",
          name: "article-list",
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "view/:articleno",
          name: "article-view",
          component: () => import("@/components/board/BoardDetail.vue"),
        },
        {
          path: "write",
          name: "article-write",
          component: () => import("@/components/board/BoardWrite.vue"),
        },
        {
          path: "modify/:articleno",
          name: "article-modify",
          component: () => import("@/components/board/BoardModify.vue"),
        },
      ],
    },
    {
      path: "/member",
      name: "member",
      component: () => import("../views/TheMemberView.vue"),
      children: [
        {
          path: "login",
          name: "member-login",
          component: () => import("@/components/member/MemberLogin.vue")
        },
        {
          path: "regist",
          name: "member-regist",
          component: () => import("@/components/member/MemberRegist.vue"),
        }
      ]
    },
    {
      path: "/attraction",
      name: "map",
      component: () => import("../views/TheMapView.vue"),
    },
    {
      path: "/detail/:contentId",
      name: "detail",
      component: () => import("../views/TheAttractionDetail.vue"),
      props: true
    },
  ],
});

export default router;
