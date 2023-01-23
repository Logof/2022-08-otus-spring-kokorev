package ru.otus.homework.hw17.mapper;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homework.hw17.dto.BookDto;
import ru.otus.homework.hw17.entity.Book;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-18T18:58:02+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private GenreMapper genreMapper;

    @Override
    public BookDto toDto(Book source) {
        if ( source == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setIsbn( source.getId() );
        bookDto.setTitle( source.getTitle() );
        bookDto.setAuthors( authorMapper.toDtos( source.getAuthors() ) );
        bookDto.setGenres( genreMapper.toDtos( source.getGenres() ) );

        return bookDto;
    }

    @Override
    public Book toEntity(BookDto source) {
        if ( source == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( source.getIsbn() );
        book.setTitle( source.getTitle() );
        book.setGenres( genreMapper.toEntitiesList( source.getGenres() ) );
        book.setAuthors( authorMapper.toEntitiesList( source.getAuthors() ) );

        return book;
    }
}
