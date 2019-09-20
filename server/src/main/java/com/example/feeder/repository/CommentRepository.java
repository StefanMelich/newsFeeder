package com.example.feeder.repository;

import com.example.feeder.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Iterable<Comment> findByPostId(long postId);
}
