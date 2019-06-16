package com.example.feeder.service;

import com.example.feeder.mappers.CommentMapper;
import com.example.feeder.mappers.CycleAvoidingMappingContext;
import com.example.feeder.mappers.PostMapper;
import com.example.feeder.model.dto.CommentDTO;
import com.example.feeder.model.dto.PostDTO;
import com.example.feeder.repository.CommentRepository;
import com.example.feeder.repository.PostRepository;
import com.example.feeder.entity.Comment;
import com.example.feeder.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostService postService;
    private final CycleAvoidingMappingContext context;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper,
                          PostService postService, CycleAvoidingMappingContext context) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.postService = postService;
        this.context = context;
    }

    public CommentDTO addComment(long postId, CommentDTO newComment) {
        PostDTO post = postService.findById(postId);
        newComment.setPost(post);
        Comment entity = commentRepository.save(commentMapper.modelToEntity(newComment, context));
        return commentMapper.entityToModel(entity, context);
    }

    public void deleteComment(long commentId) {
        commentRepository.deleteById(commentId);
    }

}
