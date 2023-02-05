package ru.otus.collectorio.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.response.category.CategoryResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-17T00:20:01+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class CategoryRequestMapperImpl implements CategoryRequestMapper {

    @Override
    public Category toCategory(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( categoryRequest.getName() );

        return category;
    }

    @Override
    public CategoryResponse toCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setName( category.getName() );

        return categoryResponse;
    }
}
