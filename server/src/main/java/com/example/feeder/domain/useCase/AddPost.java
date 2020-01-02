package com.example.feeder.domain.useCase;

import com.example.feeder.domain.entity.Post;
import com.example.feeder.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class AddPost {

    private final PostRepository postRepository;

    public AddPost(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post execute(Post newPost) {
        return this.postRepository.save(newPost);
    }
}
