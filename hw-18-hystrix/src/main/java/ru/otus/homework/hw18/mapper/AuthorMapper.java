package ru.otus.homework.hw18.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw18.dto.AuthorDto;
import ru.otus.homework.hw18.entity.Author;

@Mapper
public interface AuthorMapper extends EntityToDtoMapper<Author, AuthorDto>, DtoToEntityMapper<AuthorDto, Author> {
}
