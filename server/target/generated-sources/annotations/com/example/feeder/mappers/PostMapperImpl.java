package com.example.feeder.mappers;

import com.example.feeder.entity.Post;
import com.example.feeder.model.dto.PostDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-09-20T17:41:06+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post modelToEntity(PostDTO postDTO) {
        if ( postDTO == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( postDTO.getId() );
        post.setContent( postDTO.getContent() );

        return post;
    }

    @Override
    public PostDTO entityToModel(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        postDTO.setId( post.getId() );
        postDTO.setContent( post.getContent() );

        return postDTO;
    }
}
