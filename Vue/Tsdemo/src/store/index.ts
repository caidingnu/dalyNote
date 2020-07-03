import Vue from 'vue'
import Vuex from 'vuex'
import user from "@/store/module/user";
import Stu from "@/store/module/stu";

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        user,
        Stu
    }
})
