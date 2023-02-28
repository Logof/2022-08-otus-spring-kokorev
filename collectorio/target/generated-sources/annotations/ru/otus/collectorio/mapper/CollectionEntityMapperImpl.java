package ru.otus.collectorio.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.otus.collectorio.entity.Collection;
import ru.otus.collectorio.payload.request.collection.CollectionRequest;
import ru.otus.collectorio.payload.response.collection.CollectionResponse;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-28T12:24:28+0500",
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

        return collectionResponse;
    }
}
