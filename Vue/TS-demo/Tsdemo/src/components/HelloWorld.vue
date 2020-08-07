<template>
    <div class="hello">
        <span>{{msgObj}}</span>
        <input v-model="parentValue">
        <button @click="changeToken">点击</button>

        <!-- value需要子组件@Prop定义，这里把驼峰化横线 -->
        <about :parent-value="parentValue"></about>
        <!-- 方法一: 驼峰转横线 -->
        <about @handle-to-parent1="handleChildValue"></about>
        <!-- 方法二: 驼峰转横线. 注意，这里是用test接收的 -->
        <about @test="handleChildValue"></about>
        <!-- 方法三: 驼峰转横线 -->
        <about @handle-to-parent3="handleChildValue"></about>


        <hr>
        <span>子组件的值{{childValue}}</span>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue, Watch} from 'vue-property-decorator';
    import {State, Mutation, Action, namespace} from 'vuex-class'
    import About from '@/views/About.vue';

    @Component({
        components: {
            "about": About
        }
    })

    export default class HelloWorld extends Vue {
        // @Prop() private msg!: string;

        private msgObj = 'Hello';
        private childValue = "";

        parentValue = "父组件传的值";

        @State(state => state.user.token)
        token: any

        @Watch("token", {deep: true, immediate: true})
        private tokenw(a: string, b: string) {
            console.log(a, b)
        }

        @Mutation("setToken")
        setToken: any
        @Action("cleanAuthData")
        cleanAuthData: any
        @Action("updateAuthData")
        updateAuthData: any

        private beforeCreate() {
            console.log("beforeCreate")
        }

        private created() {
            console.log("create")
        }

        private mounted() {
            console.log("mounted")
            console.log(this.token, "---")
        }

        changeToken() {
            const token = {
                token: "改变=Action",
                expire: 99
            }
            this.updateAuthData(token)

            this.setToken("Mutation")
        }


        // 处理子组件传过来的值 val：是自定义的
        private handleChildValue(val: string) {
            console.log("------",val)
            // val: 子组件传过来的值
            this.childValue = val;
        }

    }


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
    h3 {
        margin: 40px 0 0;
    }

    ul {
        list-style-type: none;
        padding: 0;
    }

    li {
        display: inline-block;
        margin: 0 10px;
    }

    a {
        color: #42b983;
    }
</style>
