package com.example.feeder.service;

import com.example.feeder.mappers.PostMapper;
import com.example.feeder.model.dto.PostDTO;
import com.example.feeder.repository.PostRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    public boolean existsById(Object id) {
        if (id == null)
            return false;
        return postRepository.existsById((long) id);
    }

    public List<PostDTO> findAllPosts() {
        // Note that the second param in StreamSupport.stream()
        // determines if the resulting Stream should be parallel or sequential.
        // You should set it true, for a parallel Stream.
        return StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .map(postMapper::entityToModel)
                .collect(Collectors.toList());
    }

    public PostDTO findById(long id) {
        // or else should return DefaultPost or EmptyPost
        return postRepository.findById(id)
                .map(postMapper::entityToModel)
                .orElseThrow(NoSuchElementException::new);
    }

    public PostDTO createPost(PostDTO post) {
        return postMapper.entityToModel(
                postRepository.save(postMapper.modelToEntity(post)));
    }

    public void updatePost(long id, PostDTO post) {
        postRepository.findById(id)
                .map(p -> {
                    p.setContent(post.getContent());
                    return postRepository.save(p);
                });
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
