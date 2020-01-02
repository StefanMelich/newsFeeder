package com.example.feeder.domain.useCase;

import com.example.feeder.domain.entity.Post;
import com.example.feeder.domain.exception.NotFoundException;
import com.example.feeder.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdatePost {

    private final PostRepository postRepository;

    public UpdatePost(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post execute(final Post updatedPost) {
        return postRepository.findById(updatedPost.getId())
                .map(p -> update(updatedPost))
                .orElseThrow(() -> new NotFoundException(updatedPost.getId()));
    }

    private Post update(final Post updatedPost) {
        return postRepository.save(updatedPost);
    }
}
