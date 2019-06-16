package com.example.feeder.service;

import com.example.feeder.mappers.CommentMapper;
import com.example.feeder.mappers.CycleAvoidingMappingContext;
import com.example.feeder.mappers.PostMapper;
import com.example.feeder.model.dto.CommentDTO;
import com.example.feeder.model.dto.PostDTO;
import com.example.feeder.repository.PostRepository;
import com.example.feeder.entity.Comment;
import com.example.feeder.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;
    private final CycleAvoidingMappingContext context;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper,
                       CommentMapper commentMapper, CycleAvoidingMappingContext context) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
        this.context = context;
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
                .map(entity -> postMapper.entityToModel(entity, context))
                .collect(Collectors.toList());
    }

    public PostDTO findById(long id) {
        return postMapper.entityToModel(postRepository.findById(id)
                .orElse(null), context);
    }

    public PostDTO createPost(PostDTO post) {
        Post entity = postRepository.save(postMapper.modelToEntity(post, context));
        return postMapper.entityToModel(entity, context);
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

    public List<CommentDTO> getAllComments(long id) {
        return postRepository.findById(id).get().getComments().stream()
                .map(entity -> commentMapper.entityToModel(entity, context))
                .collect(Collectors.toList());
    }
}
