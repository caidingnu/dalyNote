<template>
    <div class="home">
        <hello-world @change-home="change" :msg="msg"/>
    </div>
</template>

<script>
    // @ is an alias to /src
    import HelloWorld from '@/components/HelloWorld.vue'
    import {Component, Vue} from "vue-property-decorator";

    @Component({
        name: 'Home',
        components: {HelloWorld}
    })
    export default class Home extends Vue {
        msg = "Welcome to Your Vue.js App"
        name = "";


        change(val) {
            // eslint-disable-next-line no-debugger
            debugger
            this.msg = val
        }
    }
</script>
