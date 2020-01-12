<template>
    <div class="main">
        <div class="log-in">
            <Login></Login>
        </div>
    </div>
</template>

<script>
    import Login from "@/components/login/login";

    export default {
        components: {
          Login
        },
        props: {},
        data() {
            return {}
        }
    }
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
    .main {
        background-image: url(../../static/img/bg.jpg);
        width: 100%;
        height: 100vh;
        background-size: cover;
        background-position: 50%;
        position: relative;
    }

    .log-in {
        position: absolute;
        width: 400px;
        height: 400px;
        top: 30%;
        right: 160px;
    }
</style>
