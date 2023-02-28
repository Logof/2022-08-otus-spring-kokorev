package ru.otus.collectorio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.collectorio.entity.Collection;
import ru.otus.collectorio.payload.request.collection.CollectionRequest;
import ru.otus.collectorio.payload.response.collection.CollectionResponse;

@Mapper
public interface CollectionEntityMapper {

    @Mapping(target = "creator", ignore = true)
    Collection toCollection(CollectionRequest request);

    CollectionResponse toCollectionResponse(Collection collection);
}
