import vue from 'vue';
import Router from 'vue-router';

vue.use(Router);
import user from "@/components/user";
import svgStu from "@/components/svgStu";
import home from "@/components/home";
export default new Router({
    routes: [
        {
            path: "/user",
            name: "user",
            component: user
        },
        {
            path: "/",
            name: "home",
            component: home
        },
        {
            path: "/svgStu",
            name: "svgStu",
            component: svgStu
        },
    ]
})