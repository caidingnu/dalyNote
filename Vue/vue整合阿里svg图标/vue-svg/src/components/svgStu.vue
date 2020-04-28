<template>
    <div>
        <h3>提供管理界面配置</h3>
        <el-row :gutter="10">
            <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1" v-for="(v,index) in svgList" :key="index">
                <div @click="show(v)" style="padding-bottom:8px;text-align: center">
                    <svg-icon :icon-class="v"></svg-icon>
                    <div>
                        {{v}}
                    </div>
                </div>
            </el-col>
        </el-row>
        <hr>
        <h3>单独使用，在src/icons/index.js中已经把Svgicon注册为全局组件,可以直接使用</h3>
        <svg-icon icon-class="督导审核"></svg-icon>
        <br>
        <p>选择展示区域</p>
        <svg-icon v-for="(x,index) in selectedSvg" :icon-class="x" :key="index"></svg-icon>

    </div>

</template>

<script>
    import svgList from "@/icons/index"

    export default {
        name: "svgStu",
        data() {
            return {
                svgList: [],
                selectedSvg: []
            }
        },
        mounted() {
            console.log(svgList)
            for (var i = 0; i < svgList.allSvg.length; i++) {
                let id = svgList.allSvg[i].default.id.replace('icon-', '');
                this.svgList.push(id)
            }
        },
        methods: {
            show(event) {
                this.$message.success('选择了' + event);
                this.selectedSvg.push(event)
            }
        }
    }
</script>

<style scoped>
    h3 {
        text-align: center;
    }
</style>