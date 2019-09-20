package com.example.feeder.mappers;

import com.example.feeder.entity.Comment;
import com.example.feeder.entity.Post;
import com.example.feeder.model.dto.CommentDTO;
import com.example.feeder.model.dto.PostDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-09-20T17:41:06+0200",
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
