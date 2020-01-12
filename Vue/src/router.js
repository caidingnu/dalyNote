import Vue from 'vue'
import VueRouter from 'vue-router' // 引入vue-router
import HelloWorld from "@/components/hello/HelloWorld";// 引入组件方式2
import Main from "@/components/main/main.vue";// 引入组件方式2
Vue.use(VueRouter);//使用vue-router
export default new VueRouter({
    routes:[
        {
            path:"/login",
            name:"login",
            meta: {
                title: '登陆'
            },
            component:()=>import('@/components/login/login') // 引入组件方式1
        },
        {
            path:'/',
            name:'HelloWorld',
            meta: {
                title: 'HelloWord'
            },
            component:HelloWorld // 引入组件方式2
        },
        {
            path:'/main',
            name:'Main',
            meta: {
                title: '首页'
            },
            component:Main // 引入组件方式2
        }
    ]
})

