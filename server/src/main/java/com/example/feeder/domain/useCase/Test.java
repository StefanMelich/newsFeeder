package com.example.feeder.domain.useCase;

import com.example.feeder.application.dto.PostDTO;
import com.example.feeder.domain.entity.Comment;
import com.example.feeder.domain.entity.Post;
import com.example.feeder.domain.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/test")
public class Test {

    private final FindAllPosts allPosts;
    private final DeletePost deletePost;
    private final CommentRepository comRepo;

    public Test(FindAllPosts allPosts, DeletePost deletePost, CommentRepository c) {
        this.allPosts = allPosts;
        this.deletePost = deletePost;
        this.comRepo = c;
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        List<Post> posts = allPosts.execute();
        if (posts.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDTO newPost) {
//        if (newPost.getId() != null) {
//            postService.existsById(newPost.getId());
//            return new ResponseEntity<>("Post with id: " + newPost.getId() + " exists.", HttpStatus.BAD_REQUEST);
//        }
//        PostDTO responsePost = postService.createPost(newPost);
//        return new ResponseEntity<>(responsePost, HttpStatus.CREATED);
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") long id) {
        deletePost.execute(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> all() {
        List<Comment> comments = (List<Comment>) comRepo.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") long id) {
        Comment comment = comRepo.findById(id).get();
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
