package ru.otus.homework.hw09.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw09.entity.Author;
import ru.otus.homework.hw09.entity.dto.AuthorDto;

@Mapper
public interface AuthorMapper extends EntityToDtoMapper<Author, AuthorDto>, DtoToEntityMapper<AuthorDto, Author> {
}
