package ru.otus.collectorio.mapper;

import org.mapstruct.Mapper;
import ru.otus.collectorio.entity.Collection;
import ru.otus.collectorio.payload.request.collection.CollectionRequest;

@Mapper
public interface CollectionEntityMapper {

    Collection toCollection(CollectionRequest request);
}
