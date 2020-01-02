package com.example.feeder.infrastructure.component;

import com.example.feeder.domain.repository.CommentRepository;
import com.example.feeder.domain.repository.PostRepository;
import com.example.feeder.domain.entity.Comment;
import com.example.feeder.domain.entity.Post;
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

        Post post = new Post("Title", "Vestibulum velit eros, quibusdam fermentum, elit blandit urna." +
                " A dui phasellus. Adipiscing placerat.");

        this.postRepository.save(post);

        for (int i = 1; i <= 5; i++) {
            String content = i + ". " + "Lorem ipsum dolor sit amet, fusce ipsum integer.";
            Comment comment = new Comment(content, post);
            commentRepository.save(comment);
        }

        this.postRepository.save(new Post("Title", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit." +
                " Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis" +
                " parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu."));

        this.postRepository.save(new Post("Title","Maecenas ipsum velit, consectetuer eu, lobortis ut, dictum at," +
                " dui. In rutrum. Sed ac dolor sit amet purus malesuada congue. In laoreet, magna id viverra tincidunt," +
                " sem odio bibendum justo, vel imperdiet sapien wisi sed libero. Suspendisse sagittis"));

    }
}