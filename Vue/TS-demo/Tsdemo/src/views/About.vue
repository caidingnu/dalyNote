<template>
    <div class="about">
        <h1>About 是HelloWorld的子组件</h1>
        <span>{{parentValue}}</span>
        <p>
            <!-- 方法一: 直接调用@Emit -->
            <button @click="handleToParent1">向父组件传值1</button>
            <!-- 方法二: 直接调用@Emit -->
            <button @click="handleToParent2">向父组件传值2</button>
            <!-- 方法三: 间接调用 -->
            <button @click="handleClickEvent">向父组件传值3</button>
        </p>
    </div>
</template>


<script lang="ts">
    import {Component, Prop, Vue, Emit} from 'vue-property-decorator';

    @Component
    export default class About extends Vue {

        //接收父组件传来
        @Prop() parentValue: string | unknown


        // 子组件向父组件传的值
        private msg = "要传递给父组件的值";

        // 方法一
        @Emit()
        private handleToParent1() {
            return this.msg;    // return将要传递的值
        }

        // 方法二: 注意，这时父组件接收值时是用@Emit("test")括号中的test接收的，test会把正面的方法名字覆盖。（test）是自定义的
        @Emit("test")
        private handleToParent2() {
            return this.msg;    // return将要传递的值
        }

        // 方法三
        // 先定义父组件接收的方法（同方法一、二）
        @Emit()
        private handleToParent3() {
            return this.msg;    // return将要传递的值
        }

        private handleClickEvent() {
            // ... 一些其它的操作
            // 然后手动调用传值
            this.handleToParent3();
        }
    }
</script>
