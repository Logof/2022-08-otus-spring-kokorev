package ru.otus.homework.hw17.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.homework.hw17.dto.AuthorDto;
import ru.otus.homework.hw17.entity.Author;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-18T18:58:02+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public Author toEntity(AuthorDto dto) {
        if ( dto == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( dto.getId() );
        author.setFullName( dto.getFullName() );

        return author;
    }

    @Override
    public AuthorDto toDto(Author entity) {
        if ( entity == null ) {
            return null;
        }

        AuthorDto authorDto = new AuthorDto();

        authorDto.setId( entity.getId() );
        authorDto.setFullName( entity.getFullName() );

        return authorDto;
    }
}
