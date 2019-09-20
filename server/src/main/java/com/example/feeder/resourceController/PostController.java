package com.example.feeder.resourceController;

import com.example.feeder.model.dto.PostDTO;
import com.example.feeder.service.PostService;
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

    public PostController(final PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        List<PostDTO> posts = postService.findAllPosts();
        if (posts.isEmpty())
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPost(@PathVariable("id") long id) {
        PostDTO post = postService.findById(id);
        return new ResponseEntity<>(Optional.ofNullable(post), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDTO newPost) {
        if (newPost.getId() != null) {
            postService.existsById(newPost.getId());
            return new ResponseEntity<>("Post with id: " + newPost.getId() + " exists.", HttpStatus.BAD_REQUEST);
        }
        PostDTO responsePost = postService.createPost(newPost);
        return new ResponseEntity<>(responsePost, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatePost(@PathVariable("id") long id, @RequestBody PostDTO post) {
        postService.updatePost(id, post);
        return new ResponseEntity<>("Post is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePost(@PathVariable("id") long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted successfully.", HttpStatus.OK);
    }

}
