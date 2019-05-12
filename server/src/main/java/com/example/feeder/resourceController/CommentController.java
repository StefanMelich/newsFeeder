package com.example.feeder.resourceController;

import com.example.feeder.entity.Comment;
import com.example.feeder.service.CommentService;
import com.example.feeder.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final PostService postService;
    private final CommentService commentService;

    @Autowired
    public CommentController(final PostService postService, final CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<?> getPostComments(@PathVariable("postId") long postId) {
        List<Comment> comments = postService.getAllComments(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@PathVariable("postId") long postId, @RequestBody Comment newComment) {
        Comment responseComment = commentService.addComment(postId, newComment);
        return new ResponseEntity<>(responseComment, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{commentId}")
    public ResponseEntity<?> deleteComment( @PathVariable("commentId") long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment is deleted successfully", HttpStatus.OK);
    }

}
