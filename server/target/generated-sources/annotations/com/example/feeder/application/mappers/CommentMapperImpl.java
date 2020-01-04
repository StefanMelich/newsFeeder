package com.example.feeder.application.mappers;

import com.example.feeder.application.dto.CommentDTO;
import com.example.feeder.application.dto.PostDTO;
import com.example.feeder.domain.entity.Comment;
import com.example.feeder.domain.entity.Post;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-03T17:31:49+0100",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment modelToEntity(CommentDTO commentDTO) {
        if ( commentDTO == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setId( commentDTO.getId() );
        comment.setContent( commentDTO.getContent() );
        comment.setPost( postDTOToPost( commentDTO.getPost() ) );

        return comment;
    }

    @Override
    public CommentDTO entityToModel(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId( comment.getId() );
        commentDTO.setContent( comment.getContent() );
        commentDTO.setPost( postToPostDTO( comment.getPost() ) );

        return commentDTO;
    }

    protected Post postDTOToPost(PostDTO postDTO) {
        if ( postDTO == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( postDTO.getId() );
        post.setContent( postDTO.getContent() );

        return post;
    }

    protected PostDTO postToPostDTO(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        postDTO.setId( post.getId() );
        postDTO.setContent( post.getContent() );

        return postDTO;
    }
}
