import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8081/api/posts/';

class CommentService {

    getAllComments(postId) {
        return axios.get(API_URL + postId + '/comments', { headers: authHeader() });
    }

    createComment(postId, comment) {
        return axios.post(API_URL + postId + '/comments', comment, { headers: authHeader() });
    }

    deleteComment(postId, commentId) {
        return axios.delete(API_URL + postId + '/comments/' + commentId ,{ headers: authHeader() });
    }

}

export default new CommentService();
