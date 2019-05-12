import Vue from "vue";
import Router from "vue-router";
import PostsList from "./components/PostsList.vue";
import PostDetail from "./components/PostDetail";
import PostAdd from "./components/PostAdd";
import PostUpdate from "./components/PostUpdate";

Vue.use(Router);

export default new Router({
    mode: "history",
    routes: [
        {
            path: "/",
            alias: "/posts",
            name: "posts-list",
            component: PostsList
        },
        {
            path: "/posts/:id",
            name: "post-details",
            component: PostDetail,
            props: true,
            children: [
                {
                    path: "update",
                    name: "post-update",
                    component: PostUpdate
                }
            ]
        },
        {
            path: "/add",
            name: "post-add",
            component: PostAdd
        }
    ]
});