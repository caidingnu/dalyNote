import Vue from 'vue'
import VueRouter from 'vue-router'
import routes from "@/router/routes";
Vue.use(VueRouter)


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
// 跳转之前
router.beforeEach((to,from,next) => {
  console.log(to);
  next()
})

// 跳转之后
// router.afterEach(to => {
//
// })

export default router
