package ru.otus.homework.hw16.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw16.dto.GenreDto;
import ru.otus.homework.hw16.entity.Genre;

@Mapper
public interface GenreMapper extends EntityToDtoMapper<Genre, GenreDto>, DtoToEntityMapper<GenreDto, Genre> {
}
