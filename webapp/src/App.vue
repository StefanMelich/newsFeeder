<template>

    <div id="app">
        <nav class="navbar navbar-expand navbar-dark bg-dark">
            <a href="/home" class="navbar-brand">NewsFeeder</a>

            <div class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a href="/home" class="nav-link">Home</a>
                </li>
                <li class="nav-item" v-if="showAdminBoard">
                    <a href="/admin" class="nav-link">Admin Board</a>
                </li>
                <li class="nav-item" v-if="currentUser">
                    <a href="/add" class="nav-link">Add new Post</a>
                </li>
            </div>

            <div class="navbar-nav ml-auto" v-if="!currentUser">
                <li class="nav-item">
                    <a href="/register" class="nav-link">Sign Up</a>
                </li>
                <li class="nav-item">
                    <a href="/login" class="nav-link">Login</a>
                </li>
            </div>

            <div class="navbar-nav ml-auto" v-if="currentUser">
                <li class="nav-item">
                    <a href="/profile" class="nav-link">{{currentUser.username}}</a>
                </li>
                <li class="nav-item">
                    <a href class="nav-link" @click="logOut">LogOut</a>
                </li>
            </div>
        </nav>

        <div class="container">
            <router-view />
        </div>
    </div>

</template>

<script>
    import 'bootstrap-css-only/css/bootstrap.min.css';
    import 'mdbvue/build/css/mdb.css';

    export default {
        name: 'app',
        computed: {
            currentUser() {
                return this.$store.state.auth.user;
            },
            showAdminBoard() {
                if (this.currentUser) {
                    return this.currentUser.roles.includes('A');
                }
                return false;
            }
        },
        methods: {
            logOut() {
                this.$store.dispatch('auth/logout');
                this.$router.push('/login');
            }
        }
    };


</script>

<style>

    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        margin-bottom: 4em;
    }

    .z-box {
        padding: 1em;
        border: 1px solid rgba(0,0,0,0.125);
        border-radius: 0.5em;
        margin-bottom: 2em;
    }

    .ten {
        margin-left: 10em;
    }

</style>
