<template>

    <!-- add comment form -->
    <form class="md-form z-depth-1 z-box" @submit.prevent="addNewComment">
        <input id="inputMDEx" type="text" class="form-control" ref="input"
               maxlength="255" placeholder="Type comment here"
               v-model="newComment"/>
        <div class="text-right">
            <mdb-btn rounded color="primary">Add comment</mdb-btn>
        </div>
    </form>

</template>

<script>
    import { mdbBtn } from 'mdbvue';

    export default {
        name: "CommentCreateForm",
        props: [],
        components: {
            mdbBtn
        },
        data() {
            return {
                newComment: ''
            }
        },
        methods: {
            addNewComment() {
                this.$emit('add-comment-event', this.newComment);
                this.$refs.input.value = '';
            }
        }
    }
</script>

<style scoped>

</style>