<template>

    <mdb-list-group-item class="item z-depth-1 z-box">
        {{ comment.content }}

        <button type="button" class="close" aria-label="Close" v-on:click="deleteComment">
            <span aria-hidden="true">×</span>
        </button>

    </mdb-list-group-item>

</template>

<script>

    import {mdbListGroupItem} from 'mdbvue';

    export default {
        name: "Comment",
        props: {
            comment: {
                id: 0,
                content: ''
            }
        },
        components: {
            mdbListGroupItem
        },
        methods: {
            deleteComment() {
                this.$emit('delete-comment', this.comment.id);
            }
        }
    }

</script>

<style scoped>

    .item {
        list-style-type: none;
        display: block;
        padding: 2em;
        color: #111;
    }

    span:before, span:after {
        content: '\00a0';
    }

</style>