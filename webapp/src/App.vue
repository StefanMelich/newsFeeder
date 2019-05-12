<template>
    <!-- Content here -->
    <div id="app">
        <div class="site-info">
            <nav>
                <router-link class="btn btn-primary" to="/">Posts</router-link>
                <router-link class="btn btn-primary" to="/add">New Post</router-link>
            </nav>
            <br>
        </div>
        <router-view/>
    </div>

</template>

<script>
    import 'bootstrap-css-only/css/bootstrap.min.css';
    import 'mdbvue/build/css/mdb.css';

    export default {
        name: "app",
    };

</script>

<style>

    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        margin-top: 2em;
        margin-bottom: 4em;
    }

    .z-box {
        padding: 1em;
        border: 1px solid rgba(0,0,0,0.125);
        border-radius: 0.5em;
        margin-bottom: 2em;
    }

</style>
