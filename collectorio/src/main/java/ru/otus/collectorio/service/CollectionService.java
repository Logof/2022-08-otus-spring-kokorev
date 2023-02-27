package ru.otus.collectorio.service;

import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.entity.Collection;
import ru.otus.collectorio.payload.request.collection.CollectionRequest;

import java.util.List;

public interface CollectionService {

    List<Collection> findAll();

    Collection save(CollectionRequest collection);

    void deleteById(Long id);

    List<CollectibleItem> findCollectableItemByCollectionId(Long id);
}
