package ru.otus.homework.hw17.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.homework.hw17.dto.GenreDto;
import ru.otus.homework.hw17.entity.Genre;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-18T18:58:02+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public Genre toEntity(GenreDto dto) {
        if ( dto == null ) {
            return null;
        }

        Genre genre = new Genre();

        genre.setId( dto.getId() );
        genre.setGenreName( dto.getGenreName() );

        return genre;
    }

    @Override
    public GenreDto toDto(Genre entity) {
        if ( entity == null ) {
            return null;
        }

        GenreDto genreDto = new GenreDto();

        genreDto.setId( entity.getId() );
        genreDto.setGenreName( entity.getGenreName() );

        return genreDto;
    }
}
