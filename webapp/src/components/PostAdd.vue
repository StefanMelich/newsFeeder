<template>
    <div>
        <mdb-modal :show="modal" @close="close">
            <mdb-modal-header>
                <mdb-modal-title>New Post</mdb-modal-title>
            </mdb-modal-header>
            <mdb-modal-body>
                <!-- main content here -->
                <mdb-input type="textarea" label="Content of a new post." outline :rows="5"
                           v-model="content"/>

                <div class="text-right">
                    <p class='text-small' v-bind:class="{'text-danger': hasError }">
                        {{ computedCountDown }}
                    </p>
                </div>
                <!-- main content here -->
            </mdb-modal-body>
            <mdb-modal-footer>
                <mdb-btn color="secondary" @click.native="close">Close</mdb-btn>
                <mdb-btn color="primary" @click.native="save">Save changes</mdb-btn>
            </mdb-modal-footer>
        </mdb-modal>
    </div>
</template>

<script>
    import { mdbModal, mdbModalHeader, mdbModalTitle, mdbModalBody, mdbModalFooter, mdbBtn, mdbInput } from 'mdbvue';
    import postService from "../auth/services/post.service";

    export default {
        name: "PostAdd",
        components: {
            mdbModal,
            mdbModalHeader,
            mdbModalTitle,
            mdbModalBody,
            mdbModalFooter,
            mdbBtn,
            mdbInput
        },
        data() {
            return {
                modal: true,
                content: '',
                hasError: false
            };
        },
        methods: {
            /* eslint-disable no-console */
            close() {
                this.modal = false;
                this.$router.push({name: 'posts-list'});
            },
            save() {
                if (!this.hasError) {
                    this.modal = false;
                    this.addPost(this.content);

                    console.log("new post released");
                } else {
                    alert("Content length is too long.");
                }
            },
            isError(remaining) {
                this.hasError = remaining < 0;
            },
            addPost(content) {
                let data = { content: content };
                postService
                    .createPost(data)
                    .then(response => {
                        console.log(response.data);
                        // redirect after response from server
                        this.$router.push({name: 'posts-list'});
                    })
                    .catch(e => console.log(e));
            }
        },
        computed: {
            computedCountDown() {
                let chars = this.content.length;
                let limit = 250;
                let remaining = (limit - chars);
                this.isError(remaining);
                return remaining + "/" + limit + " characters remaining";
            }
        },
    }
</script>

<style scoped>

</style>