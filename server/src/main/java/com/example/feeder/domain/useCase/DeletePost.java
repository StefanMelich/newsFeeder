package com.example.feeder.domain.useCase;

import com.example.feeder.domain.entity.Post;
import com.example.feeder.domain.exception.NotFoundException;
import com.example.feeder.domain.repository.CommentRepository;
import com.example.feeder.domain.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class DeletePost {

    private final PostRepository postRepository;

    public DeletePost(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void execute(long id) {
        postRepository.deleteById(id);
    }

}
