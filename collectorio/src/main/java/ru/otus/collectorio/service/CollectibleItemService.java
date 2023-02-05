package ru.otus.collectorio.service;

import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemRequest;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemResponse;

import java.util.List;

public interface CollectibleItemService {
    List<CollectibleItemResponse> findAll();

    CollectibleItemResponse findById(Long id);

    CollectibleItem save(CollectibleItemRequest item);

    void deleteById(Long id);
}
