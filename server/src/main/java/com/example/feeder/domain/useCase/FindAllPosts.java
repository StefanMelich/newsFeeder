package com.example.feeder.domain.useCase;

import com.example.feeder.domain.entity.Post;
import com.example.feeder.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllPosts {

    private final PostRepository postRepository;

    public FindAllPosts(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> execute() {
        return (List<Post>) postRepository.findAll();
    }
}
