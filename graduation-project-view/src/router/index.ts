import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'

export const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'abonementView',
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
          {
            path: "order",
            name: "历史订单",
            component: () => import("@/components/abonement/order.vue"),
            props: {
              icon: 'Checked',
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
  {
    path: '/employeeView',
    name: 'employeeView',
    component: () => import("@/views/employeeView.vue"),
    children: [
      {
        path: "shoping",
        name: "店铺功能",
        props: {
          icon: 'Ship',
        },
        children: [
          {
            path: "activity",
            name: "活动管理",
            component: () => import("@/components/employee/activity.vue"),
            props: {
              icon: 'Mug',
            }
          },
          {
            path: "employeeType",
            name: "员工类型管理",
            component: () => import("@/components/employee/employeeType.vue"),
            props: {
              icon: 'GoldMedal',
            }
          },
          {
            path: "goodsType",
            name: "菜品类型管理",
            component: () => import("@/components/employee/goodsType.vue"),
            props: {
              icon: 'Bell',
            }
          },
        ]
      },
      {
        path: "messeage",
        name: "信息功能",
        props: {
          icon: 'Timer',
        },
        children: [
          {
            path: "goods",
            name: "菜品管理",
            component: () => import("@/components/employee/goods.vue"),
            props: {
              icon: 'KnifeFork',
            }
          },
          {
            path: "employee",
            name: "员工管理",
            component: () => import("@/components/employee/employee.vue"),
            props: {
              icon: 'User',
            }
          },
          {
            path: "incomeState",
            name: "收入报表",
            component: () => import("@/components/employee/incomeState.vue"),
            props: {
              icon: 'Money',
            }
          },
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
