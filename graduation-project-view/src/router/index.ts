import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

export const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'abonement',
    component: () => import("@/views/abonementView.vue"),
    children: [
      {
        path: "shoping",
        name: "点餐功能",
        props: {
          icon: 'ShoppingCart',
        },
        children: [
          {
            path: "order",
            name: "去点餐",
            component: () => import("@/components/abonement/order.vue"),
            props: {
              icon: 'Food',
            }
          },
          {
            path: "shoppingCart",
            name: "购物车",
            component: () => import("@/components/abonement/shoppingCart.vue"),
            props: {
              icon: 'ShoppingCart',
            }
          },
          {
            path: "reserve",
            name: "预约餐桌",
            component: () => import("@/components/abonement/reserve.vue"),
            props: {
              icon: 'Bell',
            }
          },
        ]
      },
      {
        path: "setting",
        name: "设置",
        props: {
          icon: 'Setting',
        },
        children: [
          {
            path: "userData",
            name: "修改个人资料",
            component: () => import("@/components/abonement/userData.vue"),
            props: {
              icon: 'User',
            }
          }
        ]
      }
    ]
  },
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
