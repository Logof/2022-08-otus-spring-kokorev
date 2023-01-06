package ru.otus.homework.hw13.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw13.dto.GenreDto;
import ru.otus.homework.hw13.entity.Genre;

@Mapper
public interface GenreMapper extends EntityToDtoMapper<Genre, GenreDto>, DtoToEntityMapper<GenreDto, Genre> {
}
