package com.example.feeder.service;

import com.example.feeder.repository.PostRepository;
import com.example.feeder.entity.Comment;
import com.example.feeder.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public boolean existsById(Object id) {
        if (id == null)
            return false;
        return postRepository.existsById((long) id);
    }

    public List<Post> findAllPosts() {
        return (List<Post>) postRepository.findAll();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public void updatePost(long id, Post newPost) {
        postRepository.findById(id)
                .map(post -> {
                    post.setContent(newPost.getContent());
                    return postRepository.save(post);
                })
                .orElseGet(() -> postRepository.save(newPost));
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    public List<Comment> getAllComments(long id) {
        return postRepository.findById(id).get().getComments();
    }
}
