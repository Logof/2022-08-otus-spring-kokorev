package ru.otus.homework.hw11.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.otus.homework.hw11.entity.Genre;
import ru.otus.homework.hw11.entity.dto.GenreDto;

import java.util.Objects;
import java.util.UUID;

@Mapper
public interface GenreMapper extends EntityToDtoMapper<Genre, GenreDto>, DtoToEntityMapper<GenreDto, Genre> {
    @AfterMapping
    default void convertNameToUpperCase(@MappingTarget Genre genre) {
        if (Objects.isNull(genre.getId())) {
            genre.setId(UUID.randomUUID());
        }
    }
}
