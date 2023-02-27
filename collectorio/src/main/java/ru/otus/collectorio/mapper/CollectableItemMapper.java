package ru.otus.collectorio.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemRequest;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemResponse;

@Mapper(
        uses = {
                CategoryMapper.class
        })
public interface CollectableItemMapper {

    CollectibleItem toCollectibleItem(CollectibleItemRequest dto);

    @Mapping(target = "category", ignore = true)
    CollectibleItemResponse toCollectibleItemResponse(CollectibleItem collectibleItem);

    @AfterMapping
    default void handleAccounts(CollectibleItem source, @MappingTarget CollectibleItemResponse target) {

    }

}
