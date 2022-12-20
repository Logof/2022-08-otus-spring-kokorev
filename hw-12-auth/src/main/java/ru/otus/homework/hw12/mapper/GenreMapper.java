package ru.otus.homework.hw12.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw12.entity.Genre;
import ru.otus.homework.hw12.entity.dto.GenreDto;

@Mapper
public interface GenreMapper extends EntityToDtoMapper<Genre, GenreDto>, DtoToEntityMapper<GenreDto, Genre> {
}
