package ru.otus.collectorio.mapper;

import org.mapstruct.Mapper;
import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemRequest;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemExtResponse;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemResponse;

@Mapper
public interface CollectableItemMapper {

    CollectibleItem toCollectibleItem(CollectibleItemRequest dto);

    CollectibleItemResponse toCollectibleItemResponse(CollectibleItem collectibleItem);

    CollectibleItemExtResponse toCollectibleItemExtResponse(CollectibleItem collectibleItem);
}
