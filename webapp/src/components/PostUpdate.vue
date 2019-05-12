<template>

    <div class="z-depth-1 z-box">
        <mdb-input type="textarea" label="Update post" outline :rows="3"
                   v-model="message"/>

        <div class="text-right">
            <p class='text-small' v-bind:class="{'text-danger': hasError }">
                {{ computedCountDown }}
            </p>
            <mdb-btn rounded color="primary" v-on:click="updatePost">Update</mdb-btn>
            <mdb-btn rounded color="default" @click="cancel">Cancel</mdb-btn>
        </div>
    </div>

</template>

<script>
    import { mdbBtn, mdbInput } from 'mdbvue';

    export default {
        name: "PostUpdate",
        props: {
            content: String
        },
        components: {
            mdbBtn, mdbInput
        },
        data() {
            return {
                message: '',
                hasError: false
            }
        },
        methods: {
            updatePost() {
                if (!this.hasError) {
                    this.$emit('update-post-event', this.message);
                } else {
                    alert("Content length is too long.");
                }
            },
            cancel() {
                this.$emit('update-post-cancel');
            },
            isError(remaining) {
                this.hasError = remaining < 0;
            }
        },
        computed: {
            computedCountDown() {
                let chars = this.message.length;
                let limit = 250;
                let remaining = (limit - chars);
                this.isError(remaining);
                return remaining + "/" + limit + " characters remaining";
            }
        },
        mounted() {
            this.message = this.content
        }
    }
</script>

<style scoped>

    .text-small {
        font-size: 0.9em;
    }

</style>