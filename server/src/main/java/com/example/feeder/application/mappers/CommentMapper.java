package com.example.feeder.application.mappers;

import com.example.feeder.domain.entity.Comment;
import com.example.feeder.application.dto.CommentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment modelToEntity(CommentDTO commentDTO);

    @InheritInverseConfiguration
    CommentDTO entityToModel(Comment comment);

}
