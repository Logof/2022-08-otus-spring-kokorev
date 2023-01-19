package ru.otus.homework.hw17.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw17.dto.AuthorDto;
import ru.otus.homework.hw17.entity.Author;

@Mapper
public interface AuthorMapper extends EntityToDtoMapper<Author, AuthorDto>, DtoToEntityMapper<AuthorDto, Author> {
}
