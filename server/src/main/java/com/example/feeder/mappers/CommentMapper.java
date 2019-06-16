package com.example.feeder.mappers;

import com.example.feeder.entity.Comment;
import com.example.feeder.model.dto.CommentDTO;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment modelToEntity(CommentDTO commentDTO, @Context CycleAvoidingMappingContext context);

    @InheritInverseConfiguration
    CommentDTO entityToModel(Comment comment, @Context CycleAvoidingMappingContext context);


}
