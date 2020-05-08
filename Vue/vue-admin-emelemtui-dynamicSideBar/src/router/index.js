import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
import menu1 from '@/views/nested/menu1/index'
import menu11 from '@/views/nested/menu1/menu1-1/index'
import menu12 from '@/views/nested/menu1/menu1-2/index'
import menu13 from '@/views/nested/menu1/menu1-3/index'
/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: {title: 'Dashboard', icon: 'dashboard'}
    }]
  },
  {
    path: '/menu',
    component: Layout,
    meta: {title: 'aaa', icon: 'dashboard'},
    children: [
      {
        path: 'menu1',
        name: 'menu1',
        component: menu1,
        meta: {title: 'Dashboard', icon: 'dashboard'}
      },
      {
        path: 'menu11',
        name: 'menu11',
        component: menu11,
        meta: {title: 'Dashboard', icon: 'dashboard'}
      },
      {
        path: 'menu12',
        name: 'menu12',
        component: menu12,
        meta: {title: 'Dashboard', icon: 'dashboard'}
      },
      {
        path: 'menu13',
        name: 'menu13',
        component: menu13,
        meta: {title: 'Dashboard', icon: 'dashboard'}
      },
    ]
  },
  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: '/form',
  //       name: 'form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'form', icon: 'form' }
  //     }
  //   ]
  // },

  // 404 page must be placed at the end !!!
  // { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({y: 0}),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
