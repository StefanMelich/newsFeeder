import Vue from "vue";
import Router from "vue-router";
import PostsList from "./components/PostsList.vue";
import PostDetail from "./components/PostDetail";
import PostAdd from "./components/PostAdd";
import PostUpdate from "./components/PostUpdate";

import Home from "./auth/Home";
import Login from "./auth/views/Login";
import Register from "./auth/views/Register";

Vue.use(Router);

const router = new Router({
    mode: "history",
    routes: [
        {
            path: "/posts",
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
        },
        {
            path: "/login",
            name: "login",
            component: Login
        },
        {
            path: "/register",
            name: "register",
            component: Register
        },
        {
            path: "/",
            alias: "/home",
            name: "home",
            component: Home
        },
        {
            path: "/profile",
            name: "profile",
            // lazy-loaded
            component: () => import("./auth/views/Profile")
        },
        {
            path: "/admin",
            name: "admin",
            // lazy-loaded
            component: () => import("./auth/views/BoardAdmin")
        },
    ]
})

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/home', '/register'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    // try to access a restricted page + not logged in
    if (authRequired && !loggedIn) {
        return next('/login');
    }

    next();
});

export default router;