package ru.otus.homework.hw07.mapper;

import org.mapstruct.Mapper;
import ru.otus.homework.hw07.entity.Genre;
import ru.otus.homework.hw07.entity.dto.GenreDto;

@Mapper
public interface GenreMapper extends EntityToDtoMapper<Genre, GenreDto>, DtoToEntityMapper<GenreDto, Genre> {
}
