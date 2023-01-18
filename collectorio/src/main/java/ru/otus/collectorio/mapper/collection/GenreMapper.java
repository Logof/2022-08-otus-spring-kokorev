package ru.otus.collectorio.mapper.collection;

import org.mapstruct.Mapper;
import ru.otus.collectorio.dto.collection.GenreDto;
import ru.otus.collectorio.entity.collection.Genre;
import ru.otus.collectorio.mapper.DtoToEntityMapper;
import ru.otus.collectorio.mapper.EntityToDtoMapper;

@Mapper
public interface GenreMapper extends EntityToDtoMapper<Genre, GenreDto>,
        DtoToEntityMapper<GenreDto, GenreDto> {
}
