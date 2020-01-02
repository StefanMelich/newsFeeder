package com.example.feeder.application.controller;

import com.example.feeder.application.dto.CommentDTO;
import com.example.feeder.application.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getPostComments(@PathVariable("postId") long postId) {
        List<CommentDTO> comments = commentService.findByPostId(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@PathVariable("postId") long postId, @RequestBody CommentDTO newComment) {
        CommentDTO responseComment = commentService.addComment(postId, newComment);
        return new ResponseEntity<>(responseComment, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{commentId}")
    public ResponseEntity<?> deleteComment( @PathVariable("commentId") long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment is deleted successfully", HttpStatus.OK);
    }

}
