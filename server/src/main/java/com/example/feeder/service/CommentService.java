package com.example.feeder.service;

import com.example.feeder.mappers.CommentMapper;
import com.example.feeder.model.dto.CommentDTO;
import com.example.feeder.model.dto.PostDTO;
import com.example.feeder.repository.CommentRepository;
import com.example.feeder.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostService postService;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper,
                          PostService postService) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postService = postService;
    }

    public CommentDTO addComment(long postId, CommentDTO newComment) {
        PostDTO post = postService.findById(postId);
        newComment.setPost(post);
        Comment entity = commentRepository.save(commentMapper.modelToEntity(newComment));
        return commentMapper.entityToModel(entity);
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<CommentDTO> findByPostId(long postId) {
        // You should set it true, for a parallel Stream.
        return StreamSupport.stream(commentRepository.findByPostId(postId).spliterator(), false)
                .map(entity -> commentMapper.entityToModel(entity))
                .collect(Collectors.toList());
    }

}
