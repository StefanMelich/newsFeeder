import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8081/api/posts/';

class PostService {

    getAllPosts() {
        return axios.get(API_URL, { headers: authHeader() });
    }

    getPostById(id) {
        return axios.get(API_URL + id, { headers: authHeader() });
    }

    createPost(post) {
        return axios.post(API_URL, post, { headers: authHeader() });
    }

    updatePost(post) {
        return axios.put(API_URL + post.id, post, { headers: authHeader() });
    }

    deletePost(id) {
        return axios.delete(API_URL + id, { headers: authHeader() });
    }
}

export default new PostService();