<template>
    <div>
        <el-button type="primary" @click="a">ElementUi</el-button>
        <br/>
        <button @click="b">外部js方法</button>
        <br/>
        <i-button @click="c" type="primary">Iview</i-button>
        <br/>
        <br/>
        <i-button type="primary" @click="open(false)">打开提醒</i-button>
        <br/>
        <label>{{$page}}</label>


    </div>
</template>

<script>
    import Utils from "../public/js/utils";

    export default {
        name: "Mbutton",
        data() {
            return {
                modal1: false
            }
        },
        methods: {
            a: function () {
                this.$message('这是一条消息提示');
            },
            b: function () {
                Utils.get()
            },
            c: function () {
                this.$Message.success("这是iview")
            },
            open (nodesc) {
                this.$Notice.success({
                    title: '这是通知标题',
                    desc: nodesc ? '' : '这里是通知描述这里,是通知描述这里是通知描述这里,是通知描述这里,是通知描述这里是通知描述这里是通知描述'
                });
            }
        }
    }
</script>

<style scoped>
    img {
        width: 30vw;
        height: 30vh;
    }
</style>