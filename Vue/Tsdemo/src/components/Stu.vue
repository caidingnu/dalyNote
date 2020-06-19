<template>
    <div>
        Stu
        <p>{{name}}</p>
        <p>{{stuName}}</p>
        <button @click="MutationChangrStuName('Mutation')">Mutation</button>
        <button @click="ActionChangrStuName('Action')">Action</button>
        <fieldset>
            <div><HelloWorld></HelloWorld></div>
        </fieldset>
    </div>
</template>

<script lang="ts">
    import {Component, Prop, Vue, Watch} from 'vue-property-decorator';
    import {State, Mutation, Action, namespace, Getter} from 'vuex-class'
    import HelloWorld from "@/components/HelloWorld.vue";

    @Component({
        components:{
            HelloWorld
        }
    })

    export default class Stu extends Vue {

        name: string | undefined = "";

        // 监听的值要从store中@State到本组件,或者传递之后定义到本页面（组件传值）
        @Watch("stuName",{deep:true,immediate:true})
        watchStuName(newV: string, oldV: string){
            console.log("new--"+newV,"old--"+oldV)
        }

        @State(state => state.stu.common.stuName)
        stuName: string | undefined;

        @Getter("getStuName")
        getStuName: any;

        @Mutation("SET_STUNAME")
        SET_STUNAME: Function | any;

        @Action("setStuName")
        setStuName: any;

        mounted() {
            this.name = this.getStuName
        }


        MutationChangrStuName(name: string){
            // 两种调用 Mutation
            // this.$store.commit("SET_STUNAME",name)
            this.SET_STUNAME(name)
        }
        ActionChangrStuName(name: string){
            // 两种调用 setStuName
            // this.$store.dispatch("setStuName",name)
            this.setStuName(name)
        }
    }
</script>

<style scoped>

</style>
