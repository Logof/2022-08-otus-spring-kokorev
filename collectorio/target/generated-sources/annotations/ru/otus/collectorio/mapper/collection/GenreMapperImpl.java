package ru.otus.collectorio.mapper.collection;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.dto.collection.GenreDto;
import ru.otus.collectorio.entity.collection.Genre;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-11T00:45:57+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public GenreDto toEntity(GenreDto dto) {
        if ( dto == null ) {
            return null;
        }

        GenreDto genreDto = new GenreDto();

        genreDto.setId( dto.getId() );
        genreDto.setName( dto.getName() );

        return genreDto;
    }

    @Override
    public GenreDto toDto(Genre entity) {
        if ( entity == null ) {
            return null;
        }

        GenreDto genreDto = new GenreDto();

        genreDto.setId( entity.getId() );
        genreDto.setName( entity.getName() );

        return genreDto;
    }
}
