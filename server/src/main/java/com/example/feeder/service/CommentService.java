package com.example.feeder.service;

import com.example.feeder.repository.CommentRepository;
import com.example.feeder.repository.PostRepository;
import com.example.feeder.entity.Comment;
import com.example.feeder.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(final CommentRepository commentRepository, final PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public Comment addComment(long postId, Comment newComment) {
        Post post = postRepository.findById(postId).get();
        newComment.setPost(post);
        return commentRepository.save(newComment);
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }

}
