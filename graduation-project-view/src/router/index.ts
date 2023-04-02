import { ElNotification } from 'element-plus'
import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

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
            path: "historyOrder",
            name: "历史订单",
            component: () => import("@/components/abonement/historyOrder.vue"),
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
            name: "员工等级管理",
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
          {
            path: "userLevel",
            name: "用户等级管理",
            component: () => import("@/components/employee/userLevel.vue"),
            props: {
              icon: 'StarFilled',
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
              icon: 'OfficeBuilding',
            }
          },
          {
            path: "user",
            name: "用户管理",
            component: () => import("@/components/employee/user.vue"),
            props: {
              icon: "User",
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
  {
    path: "/abonementLogin",
    name: "用户登录",
    component: () => import("@/views/abonementLogin.vue")
  },
  {
    path: "/employeeLogin",
    name: "后台登录",
    component: () => import("@/views/employeeLogin.vue")
  },
  {
    path: "/register",
    name: "用户注册",
    component: () => import("@/views/register.vue")
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

const filterPath = ["/employeeLogin", "/abonementLogin", "/register"]

router.beforeEach((to, from, next) => {
  const userId = localStorage.getItem('userId')
  const userName = localStorage.getItem('userName')
  const nickName = localStorage.getItem('nickName')
  const identity = localStorage.getItem('identity') // user、employee
  const identityType = localStorage.getItem('identityType')
  const token = localStorage.getItem('token')
  
  if (filterPath.indexOf(to.path) !== -1) {
    next();
  } else if (token === "" || token === null || userName === "" || nickName === "") {
    ElNotification({ title: '没有登录！即将跳转！', type: 'warning' })
    setTimeout(async function () {
      if (to.path.indexOf("/employeeView") != -1) {
        await router.push("/employeeLogin")
      } else {
        await router.push("/abonementLogin")
      }
      await router.go(0)
    }, 1000)
  }
  if (to.path.indexOf("/employee/") != -1) {
    if (identity != "employee" && token != "") {
      ElNotification({ title: '你不是管理员！,即将跳转！', type: 'warning' })
      setTimeout(async function () {
        await router.push("/")
        await router.go(0)
      }, 1000)
    }
  }
  next()
})

export default router
