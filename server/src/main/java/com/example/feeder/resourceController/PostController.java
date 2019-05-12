package com.example.feeder.resourceController;

import com.example.feeder.entity.Post;
import com.example.feeder.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        List<Post> posts = postService.findAllPosts();
        if (posts.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") long id) {
        Optional<Post> post = postService.findById(id);
        return new ResponseEntity<>(Optional.ofNullable(post), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post newPost) {
        if (newPost.getId() != null) {
            postService.existsById(newPost.getId());
            return new ResponseEntity<>("Post with id: " + newPost.getId() + " exists.", HttpStatus.BAD_REQUEST);
        }
        Post responsePost = postService.createPost(newPost);
        return new ResponseEntity<>(responsePost, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") long id, @RequestBody Post post) {
        postService.updatePost(id, post);
        return new ResponseEntity<>("Post is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted successfully.", HttpStatus.OK);
    }

}
