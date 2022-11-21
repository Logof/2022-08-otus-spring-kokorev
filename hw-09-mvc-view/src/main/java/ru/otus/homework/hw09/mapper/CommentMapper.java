package ru.otus.homework.hw09.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw09.entity.Comment;
import ru.otus.homework.hw09.entity.dto.CommentDto;

@Mapper
public interface CommentMapper extends EntityToDtoMapper<Comment, CommentDto>, DtoToEntityMapper<CommentDto, Comment> {
}
