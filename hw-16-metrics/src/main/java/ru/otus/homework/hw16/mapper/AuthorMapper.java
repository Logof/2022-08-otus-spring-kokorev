package ru.otus.homework.hw16.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw16.dto.AuthorDto;
import ru.otus.homework.hw16.entity.Author;

@Mapper
public interface AuthorMapper extends EntityToDtoMapper<Author, AuthorDto>, DtoToEntityMapper<AuthorDto, Author> {
}
