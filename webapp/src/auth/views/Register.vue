<template>

    <mdb-container fluid>
        <mdb-row class="justify-content-center">
            <mdb-col col="6">

                <div v-if="!successful">
                    <form class="md-form z-depth-1 z-box" @submit.prevent="handleRegister">
                        <p class="h4 text-center mb-4">Sign in</p>
                        <div class="grey-text">
                            <mdb-input
                                    label="Username"
                                    icon="user"
                                    type="text"
                                    v-model="user.username"
                                    v-validate="'required|min:3|max:20'"/>
                            <div class="alert-danger" v-if="submitted && errors.has('username')">
                                {{errors.first('username')}}
                            </div>

                            <mdb-input label="Email"  icon="envelope" type="email" v-model="user.email"/>
                            <mdb-input
                                    label="Your password"
                                    icon="lock"
                                    type="password"
                                    v-model="user.password"
                                    v-validate="'required|min:6|max:40'"/>
                            <div class="alert-danger" v-if="submitted && errors.has('password')">
                                {{errors.first('password')}}
                            </div>
                        </div>
                        <div class="text-center">
                            <mdb-btn color="primary">Login</mdb-btn>
                        </div>
                        <div class="alert"
                             :class="successful ? 'alert-success' : 'alert-danger'"
                             v-if="message">
                            {{message}}
                        </div>
                    </form>
                </div>

            </mdb-col>
        </mdb-row>
    </mdb-container>
</template>

<script>
    import User from '../models/user';
    import { mdbContainer, mdbRow, mdbCol,  mdbInput, mdbBtn, } from 'mdbvue';

    export default {
        name: 'register',
        components: {
            mdbContainer, mdbRow, mdbCol, mdbInput, mdbBtn
        },
        computed: {
            loggedIn() {
                return this.$store.state.auth.status.loggedIn;
            }
        },
        data() {
            return {
                user: new User('', '', '', 'A'),
                submitted: false,
                successful: false,
                message: ''
            };
        },
        mounted() {
            if (this.loggedIn) {
                this.$router.push('/profile');
            }
        },
        methods: {
            handleRegister() {
                this.message = '';
                this.submitted = true;
                this.$validator.validate().then(valid => {
                    if (valid) {
                        this.$store.dispatch('auth/register', this.user)
                            .then(
                            data => {
                                this.message = data.message;
                                this.successful = true;
                                this.$router.push('/profile');
                            },
                            error => {
                                this.message = error.message;
                                this.successful = false;
                            }
                        );
                    }
                    else {
                        window.alert("Username or password is too short.");
                    }
                });
            }
        }
    };
</script>

<style scoped>

    * {
        text-align: left;
    }

</style>