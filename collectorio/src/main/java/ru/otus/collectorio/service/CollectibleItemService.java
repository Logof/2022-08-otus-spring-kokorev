package ru.otus.collectorio.service;

import ru.otus.collectorio.payload.request.collectible.CollectibleItemExtRequest;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemRequest;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemExtResponse;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemResponse;

import java.util.List;

public interface CollectibleItemService {
    List<CollectibleItemResponse> findAll();

    CollectibleItemResponse findById(Long id);

    CollectibleItemResponse save(CollectibleItemRequest item);

    CollectibleItemExtResponse save(CollectibleItemExtRequest item);

    void deleteById(Long id);

    List<CollectibleItemResponse> findCollectableItemByCollectionId(Long id);
}
