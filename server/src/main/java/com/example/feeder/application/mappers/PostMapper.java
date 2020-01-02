package com.example.feeder.application.mappers;

import com.example.feeder.domain.entity.Post;
import com.example.feeder.application.dto.PostDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post modelToEntity(PostDTO postDTO);

    @InheritInverseConfiguration
    PostDTO entityToModel(Post post);


}
