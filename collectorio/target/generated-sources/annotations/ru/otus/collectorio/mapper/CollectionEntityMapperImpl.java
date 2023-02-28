package ru.otus.collectorio.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.Category;
import ru.otus.collectorio.entity.Collection;
import ru.otus.collectorio.payload.request.category.CategoryRequest;
import ru.otus.collectorio.payload.request.collection.CollectionRequest;
import ru.otus.collectorio.payload.response.category.CategoryResponse;
import ru.otus.collectorio.payload.response.collection.CollectionResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-28T11:04:54+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class CollectionEntityMapperImpl implements CollectionEntityMapper {

    @Override
    public Collection toCollection(CollectionRequest request) {
        if ( request == null ) {
            return null;
        }

        Collection collection = new Collection();

        collection.setId( request.getId() );
        collection.setName( request.getName() );
        collection.setCategory( categoryRequestToCategory( request.getCategory() ) );

        return collection;
    }

    @Override
    public CollectionResponse toCollectionResponse(Collection collection) {
        if ( collection == null ) {
            return null;
        }

        CollectionResponse collectionResponse = new CollectionResponse();

        collectionResponse.setId( collection.getId() );
        collectionResponse.setName( collection.getName() );
        collectionResponse.setCategory( categoryToCategoryResponse( collection.getCategory() ) );

        return collectionResponse;
    }

    protected Category categoryRequestToCategory(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryRequest.getId() );
        category.setName( categoryRequest.getName() );

        return category;
    }

    protected CategoryResponse categoryToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setName( category.getName() );

        return categoryResponse;
    }
}
