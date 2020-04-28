import Vue from 'vue'
import App from './App.vue'
import element from 'element-ui';
import router from './router/index'
import '@/icons';//引入svg模板
Vue.config.productionTip = false;
Vue.use(element)
new Vue({
    router,
    render: h => h(App),
}).$mount('#app');
