<template>

    <mdb-container fluid>
        <mdb-row class="justify-content-center">
            <mdb-col col="6">

                <form class="md-form z-depth-1 z-box" @submit.prevent="handleLogin">
                    <p class="h4 text-center mb-4">Sign in</p>
                    <div class="grey-text">
                        <mdb-input
                                label="Your username"
                                icon="user"
                                type="text"
                                v-model="username"/>
                        <mdb-input
                                label="Your password"
                                icon="lock"
                                type="password"
                                v-model="password"/>
                    </div>
                    <div class="text-center">
                        <mdb-btn color="primary">Login</mdb-btn>
                    </div>
                    <mdb-alert color="danger" v-if="unauthorized">
                        Incorrect username or password.
                    </mdb-alert>
                </form>

            </mdb-col>
        </mdb-row>
    </mdb-container>

</template>

<script>
    import { mdbContainer, mdbRow, mdbCol,  mdbInput, mdbBtn, mdbAlert } from 'mdbvue';
    import User from '../models/user';

    export default {
        name: 'login',
        components: {
           mdbContainer, mdbRow, mdbCol, mdbInput, mdbBtn, mdbAlert
        },
        data() {
            return {
                username: '',
                password: '',
                unauthorized: false
            };
        },
        mounted() {
            // if user is loggedIn -> redirect to profile page
            let loggedIn = this.$store.state.auth.status.loggedIn;
            if (loggedIn) {
                this.$router.push('/profile');
            }
        },
        methods: {
            handleLogin() {
                this.unauthorized = false;
                this.$validator.validateAll();

                if (this.errors.any()) {
                    this.unauthorized = true;
                    return;
                }

                if (this.username && this.password) {
                    let user = new User(this.username, '', this.password);
                    /* eslint-disable no-console */
                    this.$store
                        .dispatch('auth/login', user)
                        .then(
                            () =>  this.$router.push('/profile'),
                            error => {
                                this.unauthorized = true;
                                console.log(error);
                        })
                        .catch(e => console.log("Error: " + e));
                }
            }
        }
    };
</script>

<style scoped>

    * {
        text-align: left;
    }

</style>