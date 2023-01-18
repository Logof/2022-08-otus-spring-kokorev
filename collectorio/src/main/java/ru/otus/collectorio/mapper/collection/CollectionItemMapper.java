package ru.otus.collectorio.mapper.collection;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import ru.otus.collectorio.dto.collection.CollectionItemDto;
import ru.otus.collectorio.entity.collection.CollectionItem;
import ru.otus.collectorio.mapper.EntityToDtoMapper;

@Mapper(
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        uses = {
                ItemMapper.class,
                CategoryMapper.class,
                GenreMapper.class
        }
)
public interface CollectionItemMapper extends EntityToDtoMapper<CollectionItem, CollectionItemDto> {
}
