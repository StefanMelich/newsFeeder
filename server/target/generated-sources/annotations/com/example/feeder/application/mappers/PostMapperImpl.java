package com.example.feeder.application.mappers;

import com.example.feeder.application.dto.PostDTO;
import com.example.feeder.domain.entity.Post;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-03T17:31:49+0100",
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
