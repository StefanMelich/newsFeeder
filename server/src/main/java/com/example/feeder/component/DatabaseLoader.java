package com.example.feeder.component;

import com.example.feeder.repository.CommentRepository;
import com.example.feeder.repository.PostRepository;
import com.example.feeder.entity.Comment;
import com.example.feeder.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public DatabaseLoader(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(String... strings) {

        Post post = new Post("Vestibulum velit eros, quibusdam fermentum, elit blandit urna." +
                " A dui phasellus. Adipiscing placerat.");

        for (int i = 1; i <= 5; i++) {
            Comment comment = new Comment(i + ". " + "Lorem ipsum dolor sit amet, fusce ipsum integer.");
            comment.setPost(post);
            post.getComments().add(comment);
        }

        this.postRepository.save(post);

        this.postRepository.save(new Post("Lorem ipsum dolor sit amet, consectetuer adipiscing elit." +
                " Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis" +
                " parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu."));

        this.postRepository.save(new Post("Maecenas ipsum velit, consectetuer eu, lobortis ut, dictum at," +
                " dui. In rutrum. Sed ac dolor sit amet purus malesuada congue. In laoreet, magna id viverra tincidunt," +
                " sem odio bibendum justo, vel imperdiet sapien wisi sed libero. Suspendisse sagittis"));

        Post post4 = new Post("Post4");
        this.postRepository.save(post4);
        Comment comment4 = new Comment("Comment4", post4);
        this.commentRepository.save(comment4);

    }
}