package ru.otus.homework.hw09.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import ru.otus.homework.hw09.entity.Book;
import ru.otus.homework.hw09.entity.dto.BookDto;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        uses = {
                AuthorMapper.class,
                GenreMapper.class
        }
)
public interface BookMapper extends EntityToDtoMapper<Book, BookDto>, DtoToEntityMapper<BookDto, Book> {
}
