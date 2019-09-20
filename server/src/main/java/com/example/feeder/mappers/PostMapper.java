package com.example.feeder.mappers;

import com.example.feeder.entity.Post;
import com.example.feeder.model.dto.PostDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post modelToEntity(PostDTO postDTO);

    @InheritInverseConfiguration
    PostDTO entityToModel(Post post);


}
