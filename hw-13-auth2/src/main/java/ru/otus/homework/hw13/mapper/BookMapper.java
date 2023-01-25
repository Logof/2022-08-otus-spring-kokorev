package ru.otus.homework.hw13.mapper;


import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.homework.hw13.dto.BookDto;
import ru.otus.homework.hw13.entity.Book;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        uses = {
                AuthorMapper.class,
                GenreMapper.class
        }
)
public interface BookMapper extends EntityToDtoMapper<Book, BookDto>, DtoToEntityMapper<BookDto, Book> {
    @Mapping(target = "isbn", source = "id")
    BookDto toDto(Book source);

    @Mapping(target = "id", source = "isbn")
    Book toEntity(BookDto source);
}
