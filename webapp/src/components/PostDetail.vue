<template>

    <mdb-container fluid>
        <mdb-row class="justify-content-center">
            <mdb-col col="6">
                <div class="z-depth-1 z-box">

                    <!-- dropdown -->
                    <mdb-dropdown class="dropdown-right">
                        <button class="close" slot="toggle" type="button" aria-label="Close">
                            <i class="fas fa-ellipsis-h"></i>
                        </button>
                        <mdb-dropdown-menu color="primary" right>
                            <mdb-dropdown-item @click.native="update = true">
                                Update
                            </mdb-dropdown-item>
                            <mdb-dropdown-item @click.native="deletePost">Delete</mdb-dropdown-item>
                            <mdb-dropdown-item>
                                <router-link to="/">Close</router-link>
                            </mdb-dropdown-item>
                        </mdb-dropdown-menu>
                    </mdb-dropdown>
                    <!-- Content of post -->
                    <p class="content">
                        {{ this.post.content }}
                    </p>
                    <!-- new action buttons group
                    <mdb-btn rounded color="danger" @click="deletePost()">Delete</mdb-btn> -->
                </div>

                <!-- update post form -->
                <post-update class="z-depth-1 z-box" v-if="update"
                             :content="this.post.content"
                             @update-post-event="updatePost($event)"
                             @update-post-cancel="updateCancel">
                </post-update>
                <!-- add comment form -->
                <comment-create-form @add-comment-event="addComment($event)" />
                <!-- comments list -->
                <mdb-list-group>
                    <comment v-for="comment in this.post.comments"
                             :key="comment.id"
                             :comment="comment"
                             @delete-comment="deleteComment($event)">
                    </comment>
                </mdb-list-group>
            </mdb-col>
        </mdb-row>
    </mdb-container>

</template>

<script>

    import { mdbContainer, mdbRow, mdbCol, mdbListGroup } from 'mdbvue';
    import { mdbDropdown, /* mdbDropdownToggle,*/ mdbDropdownMenu, mdbDropdownItem } from 'mdbvue';
    import CommentCreateForm from "./CommentCreateForm";
    import Comment from "./Comment";
    import PostUpdate from "./PostUpdate";
    import postService from "../auth/services/post.service";
    import commentService from "../auth/services/comment.service";

    export default {
        name: "Post",
        props: ["paramPost"],
        components: {
            CommentCreateForm, Comment, PostUpdate,
            mdbListGroup, mdbContainer, mdbRow, mdbCol,
            mdbDropdown, mdbDropdownMenu, mdbDropdownItem
        },
        data() {
            return {
                post: this.paramPost,
                update: false
            };
        },
        mounted() {
            /* eslint-disable no-console */
            commentService
                .getAllComments(this.post.id)
                .then(response => {
                    this.post.comments = response.data;
                    console.log(response.data);
                }).catch(e => console.log(e));
        },
        methods: {
            /* eslint-disable no-console */
            updatePost(newContent) {
                console.log(newContent);
                this.post.content = newContent;
                this.update = false;
                let data = {
                    id: this.post.id,
                    content: newContent,
                };
                postService
                    .updatePost(data)
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            updateCancel() {
                this.update = false;
            },
            addComment(newComment) {
                if (newComment.length != 0)
                {
                    let data = { content: newComment };
                    commentService.createComment(this.post.id, data)
                        .then(response => {
                            console.log("response from server after post comment");
                            console.log(response);
                            this.post.comments.push(response.data);
                        })
                        .catch(e => {
                            console.log(e);
                        });
                }
            },
            deleteComment(commentId) {
                commentService.deleteComment(this.post.id, commentId)
                    .then(response => {
                        console.log(response.data);
                        let index = this.post.comments.findIndex(c => c.id === commentId);
                        console.log(index);
                        this.post.comments.splice(index, 1);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            deletePost() {
                postService.deletePost(this.post.id)
                    .then(response => {
                        console.log(response.data);
                        this.$emit("refreshData");
                        this.$router.push('/');
                    })
                    .catch(e => {
                        console.log(e);
                    });
            }
        }
    };

</script>

<style scoped>

    span:before, span:after {
        content: '\00a0';
    }

    .content {
        margin: 2em;
    }

    /* from comment-list */
    comment {
        margin-top: 1em;
    }

    .list-group-item:first-child, .list-group-item:last-child {
        border-radius: 0.5em;
    }

    ul li {
        margin: 0em 1em 0.5em 1em;
        padding: 2em;
        border-radius: 0.5em;
    }

    list {
        margin-bottom: 5em;
    }

    .dropdown-right {
         float: right;
     }

</style>