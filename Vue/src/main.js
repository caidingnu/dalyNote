import Vue from 'vue'
import App from './App.vue'
import iView from 'iview';
import 'iview/dist/styles/iview.css';    // 使用 CSS
import router from "./router";
//引入api
import api from '../src/api/index'
// 引用axios
const axios = require('axios');
axios.defaults.withCredentials = true;

// 将API方法绑定到全局
Vue.prototype.$api = api;

Vue.use(iView);
Vue.config.productionTip = false;



//全局设置提示框
// Vue.prototype.$Message.config({
//   top: 100,
//   duration: 3
// });
// Vue.prototype.$Notice.config({
//   top: 50,
//   duration: 3
// });
new Vue({
    router,
    render: h => h(App),
}).$mount('#app');


//页面标题设置
router.beforeEach((to, from, next) => {
    /* 路由发生变化修改页面meta */
    if (to.meta.content) {
        let head = document.getElementsByTagName('head');
        let meta = document.createElement('meta');
        meta.content = to.meta.content;
        head[0].appendChild(meta)
    }
    /* 路由发生变化修改页面title */
    if (to.meta.title) {
        document.title = to.meta.title;
    }
    next()
});
