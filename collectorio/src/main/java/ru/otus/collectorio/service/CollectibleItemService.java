package ru.otus.collectorio.service;

import ru.otus.collectorio.entity.CollectibleItem;

import java.util.List;

public interface CollectibleItemService {
    List<CollectibleItem> findAll();

    CollectibleItem findById(Long id);

    CollectibleItem save(CollectibleItem item);

    void deleteById(Long id);

    List<CollectibleItem> findByCollection(Long id);
}
