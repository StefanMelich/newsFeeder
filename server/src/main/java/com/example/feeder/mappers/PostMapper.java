package com.example.feeder.mappers;

import com.example.feeder.entity.Post;
import com.example.feeder.model.dto.PostDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post modelToEntity(PostDTO postDTO, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    PostDTO entityToModel(Post post, @Context CycleAvoidingMappingContext context);


}
