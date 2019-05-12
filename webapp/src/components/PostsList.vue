<template>

    <mdb-container fluid>
        <mdb-row class="justify-content-center">
            <mdb-col col="6">
                
                <mdb-list-group>
                    <mdb-list-group-item v-for="(post, index) in posts"
                                         :key="index"
                                         class="hoverable">
                        <router-link :to="{ name: 'post-details',
                                            params: { post: post, id: post.id }}"
                                     >
                            {{ post.content }}
                        </router-link>
                        <mdb-badge class="badge" color="primary" pill>
                            {{ post.comments.length }}
                        </mdb-badge>
                    </mdb-list-group-item>
                </mdb-list-group>

            </mdb-col>
            <router-view/>
        </mdb-row>
    </mdb-container>

</template>

<script>
    import {mdbListGroup, mdbListGroupItem, mdbBadge} from 'mdbvue';
    import {mdbContainer, mdbRow, mdbCol} from 'mdbvue';
    import http from "../http-common";

    export default {
        name: "PostsList",
        props: ['Post'],
        components: {
            mdbContainer,
            mdbRow,
            mdbCol,
            mdbListGroup,
            mdbListGroupItem,
            mdbBadge
        },
        data() {
            return {
                posts: []
            };
        },
        methods: {
            /* eslint-disable no-console */
            retrievePosts() {
                http.get("/posts")
                    .then(response => {
                        this.posts = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            refreshList() {
                this.retrievePosts();
            },

        },
        mounted() {
            this.retrievePosts();
        }

    }
</script>

<style scoped>

    * {
        text-decoration: none;
    }

    .list-group-item:first-child, .list-group-item:last-child {
        border-radius: 0.5em;
    }

    ul li {
        margin: 0em 1em 1em 1em;
        padding: 2em;
        border-radius: 0.5em;
    }

    list {
        margin-bottom: 5em;
    }

    .badge {
        background: #5296F5;
        margin-left: 2em;
    }

</style>