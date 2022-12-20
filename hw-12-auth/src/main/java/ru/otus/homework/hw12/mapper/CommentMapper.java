package ru.otus.homework.hw12.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw12.entity.Comment;
import ru.otus.homework.hw12.entity.dto.CommentDto;

@Mapper
public interface CommentMapper extends EntityToDtoMapper<Comment, CommentDto>, DtoToEntityMapper<CommentDto, Comment> {
}
