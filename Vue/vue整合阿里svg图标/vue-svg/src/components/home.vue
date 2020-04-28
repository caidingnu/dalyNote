<template>
    <div>
        <router-link to="/svgStu">
            <el-button type="primary">整合阿里图标库-svg展示</el-button>
        </router-link>
        <br> <br> <br>
        <router-link to="/user" v-for="x in 3" :key="x">
            <el-button type="success">axios请求</el-button>
        </router-link>
    </div>

</template>

<script>
    export default {
        name: "home",
        data() {
            return {}
        },
        mounted() {

        },
        methods: {}
    }
</script>

<style scoped>

</style>