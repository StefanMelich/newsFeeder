package com.example.feeder.mappers;

import com.example.feeder.entity.Comment;
import com.example.feeder.entity.Post;
import com.example.feeder.model.dto.CommentDTO;
import com.example.feeder.model.dto.PostDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-06-16T14:57:23+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment modelToEntity(CommentDTO commentDTO, CycleAvoidingMappingContext context) {
        Comment target = context.getMappedInstance( commentDTO, Comment.class );
        if ( target != null ) {
            return target;
        }

        if ( commentDTO == null ) {
            return null;
        }

        Comment comment = new Comment();

        context.storeMappedInstance( commentDTO, comment );

        comment.setId( commentDTO.getId() );
        comment.setContent( commentDTO.getContent() );
        comment.setPost( postDTOToPost( commentDTO.getPost(), context ) );

        return comment;
    }

    @Override
    public CommentDTO entityToModel(Comment comment, CycleAvoidingMappingContext context) {
        CommentDTO target = context.getMappedInstance( comment, CommentDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( comment == null ) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        context.storeMappedInstance( comment, commentDTO );

        commentDTO.setId( comment.getId() );
        commentDTO.setContent( comment.getContent() );
        commentDTO.setPost( postToPostDTO( comment.getPost(), context ) );

        return commentDTO;
    }

    protected List<Comment> commentDTOListToCommentList(List<CommentDTO> list, CycleAvoidingMappingContext context) {
        List<Comment> target = context.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<Comment> list1 = new ArrayList<Comment>( list.size() );
        context.storeMappedInstance( list, list1 );

        for ( CommentDTO commentDTO : list ) {
            list1.add( modelToEntity( commentDTO, context ) );
        }

        return list1;
    }

    protected Post postDTOToPost(PostDTO postDTO, CycleAvoidingMappingContext context) {
        Post target = context.getMappedInstance( postDTO, Post.class );
        if ( target != null ) {
            return target;
        }

        if ( postDTO == null ) {
            return null;
        }

        Post post = new Post();

        context.storeMappedInstance( postDTO, post );

        post.setId( postDTO.getId() );
        post.setContent( postDTO.getContent() );
        post.setComments( commentDTOListToCommentList( postDTO.getComments(), context ) );

        return post;
    }

    protected List<CommentDTO> commentListToCommentDTOList(List<Comment> list, CycleAvoidingMappingContext context) {
        List<CommentDTO> target = context.getMappedInstance( list, List.class );
        if ( target != null ) {
            return target;
        }

        if ( list == null ) {
            return null;
        }

        List<CommentDTO> list1 = new ArrayList<CommentDTO>( list.size() );
        context.storeMappedInstance( list, list1 );

        for ( Comment comment : list ) {
            list1.add( entityToModel( comment, context ) );
        }

        return list1;
    }

    protected PostDTO postToPostDTO(Post post, CycleAvoidingMappingContext context) {
        PostDTO target = context.getMappedInstance( post, PostDTO.class );
        if ( target != null ) {
            return target;
        }

        if ( post == null ) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        context.storeMappedInstance( post, postDTO );

        postDTO.setId( post.getId() );
        postDTO.setContent( post.getContent() );
        postDTO.setComments( commentListToCommentDTOList( post.getComments(), context ) );

        return postDTO;
    }
}
