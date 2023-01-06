package ru.otus.homework.hw13.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw13.dto.AuthorDto;
import ru.otus.homework.hw13.entity.Author;

@Mapper
public interface AuthorMapper extends EntityToDtoMapper<Author, AuthorDto>, DtoToEntityMapper<AuthorDto, Author> {
}
