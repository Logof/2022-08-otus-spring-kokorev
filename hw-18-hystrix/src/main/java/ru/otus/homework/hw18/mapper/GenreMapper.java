package ru.otus.homework.hw18.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw18.dto.GenreDto;
import ru.otus.homework.hw18.entity.Genre;

@Mapper
public interface GenreMapper extends EntityToDtoMapper<Genre, GenreDto>, DtoToEntityMapper<GenreDto, Genre> {
}
