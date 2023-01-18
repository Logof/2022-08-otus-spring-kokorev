package ru.otus.collectorio.mapper.collection;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.dto.collection.CategoryDto;
import ru.otus.collectorio.entity.collection.Category;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-11T00:45:57+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        Category category = new Category();

        category.setPlatformName( dto.getPlatformName() );

        return category;
    }

    @Override
    public CategoryDto toDto(Category entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( entity.getId() );
        categoryDto.setPlatformName( entity.getPlatformName() );

        return categoryDto;
    }
}
