package com.example.feeder.mappers;

import com.example.feeder.entity.Comment;
import com.example.feeder.model.dto.CommentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment modelToEntity(CommentDTO commentDTO);

    @InheritInverseConfiguration
    CommentDTO entityToModel(Comment comment);

}
