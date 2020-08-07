<template>
    <div class="hello">
        <h1>{{ msg }}</h1>
        <button @click="change1">改变1</button>
        <button @click="change2">改变2</button>
    </div>
</template>

<script lang="ts">
    import {Component, Emit, Prop, Vue} from 'vue-property-decorator';

    @Component
    export default class HelloWorld extends Vue {
        @Prop({
            type:String,
            default:""
        }) private msg!: string;

        @Emit()
        changeHome(val: string) {
            return val
        }
        change2(){
            // eslint-disable-next-line no-debugger
            this.changeHome("改变2")
        }
        change1(){
            this.changeHome("改变1")
        }

    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">

</style>
