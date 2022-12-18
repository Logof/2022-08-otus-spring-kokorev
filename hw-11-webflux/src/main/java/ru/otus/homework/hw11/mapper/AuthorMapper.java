package ru.otus.homework.hw11.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.otus.homework.hw11.entity.Author;
import ru.otus.homework.hw11.entity.dto.AuthorDto;

import java.util.Objects;
import java.util.UUID;

@Mapper
public interface AuthorMapper extends EntityToDtoMapper<Author, AuthorDto>, DtoToEntityMapper<AuthorDto, Author> {

    @AfterMapping
    default void convertNameToUpperCase(@MappingTarget Author author) {
        if (Objects.isNull(author.getId())) {
            author.setId(UUID.randomUUID());
        }
    }
}
