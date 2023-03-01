package ru.otus.homework.hw17.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw17.dto.GenreDto;
import ru.otus.homework.hw17.entity.Genre;

@Mapper
public interface GenreMapper extends EntityToDtoMapper<Genre, GenreDto>, DtoToEntityMapper<GenreDto, Genre> {
}
