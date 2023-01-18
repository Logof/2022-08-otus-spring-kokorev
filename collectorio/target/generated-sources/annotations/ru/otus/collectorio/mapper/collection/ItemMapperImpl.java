package ru.otus.collectorio.mapper.collection;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.dto.collection.CategoryDto;
import ru.otus.collectorio.dto.collection.GenreDto;
import ru.otus.collectorio.dto.collection.ItemDto;
import ru.otus.collectorio.entity.collection.Category;
import ru.otus.collectorio.entity.collection.Genre;
import ru.otus.collectorio.entity.collection.Item;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-11T00:45:57+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public Item toEntity(ItemDto dto) {
        if ( dto == null ) {
            return null;
        }

        Item item = new Item();

        item.setName( dto.getName() );
        item.setNameAlt( dto.getNameAlt() );
        item.setCategory( categoryDtoToCategory( dto.getCategory() ) );
        item.setGenre( genreDtoToGenre( dto.getGenre() ) );
        item.setReleaseType( dto.getReleaseType() );
        item.setDescription( dto.getDescription() );

        return item;
    }

    @Override
    public ItemDto toDto(Item entity) {
        if ( entity == null ) {
            return null;
        }

        ItemDto itemDto = new ItemDto();

        itemDto.setId( entity.getId() );
        itemDto.setName( entity.getName() );
        itemDto.setNameAlt( entity.getNameAlt() );
        itemDto.setCategory( categoryToCategoryDto( entity.getCategory() ) );
        itemDto.setGenre( genreToGenreDto( entity.getGenre() ) );
        itemDto.setReleaseType( entity.getReleaseType() );
        itemDto.setDescription( entity.getDescription() );

        return itemDto;
    }

    protected Category categoryDtoToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setPlatformName( categoryDto.getPlatformName() );

        return category;
    }

    protected Genre genreDtoToGenre(GenreDto genreDto) {
        if ( genreDto == null ) {
            return null;
        }

        Genre genre = new Genre();

        genre.setName( genreDto.getName() );

        return genre;
    }

    protected CategoryDto categoryToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setPlatformName( category.getPlatformName() );

        return categoryDto;
    }

    protected GenreDto genreToGenreDto(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreDto genreDto = new GenreDto();

        genreDto.setId( genre.getId() );
        genreDto.setName( genre.getName() );

        return genreDto;
    }
}
