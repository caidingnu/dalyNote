<template>
    <div class="hello">
        <el-button @click="userinfo" type="primary">axios请求测试</el-button>
    </div>
</template>

<script>
    import {getPersonInfo} from '../api/api'
    export default {
        name: 'HelloWorld',
        props: {},
        data() {
            return {
                user: [],
            }
        },
        methods: {
            userinfo() {
                getPersonInfo().then(res => {
                    this.user = res.data
                });
                console.log(this.user)
            },
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    p:hover {
        cursor: pointer;
    }
</style>
