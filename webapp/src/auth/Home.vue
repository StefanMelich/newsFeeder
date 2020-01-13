<template>
    <div class="container">
        <header class="jumbotron">
            <h2>Welcome to NewsFeeder.</h2>
            <h4 v-if="loggedIn">Private content for registered users only.</h4>
        </header>
        <post></post>
    </div>
</template>

<script>
    import UserService from './services/user.service';
    import Post from '../components/PostsList';

    export default {
        name: 'home',
        components: {
            Post
        },
        data() {
            return {
                content: ''
            };
        },
        mounted() {
            UserService.getPublicContent().then(
                response => {
                    this.content = response.data;
                },
                error => {
                    this.content = error.response.data.message;
                }
            );
        },
        methods: {
            loggedIn() {
                return !this.$store.state.auth.status.loggedIn;
            }
        }
    };
</script>