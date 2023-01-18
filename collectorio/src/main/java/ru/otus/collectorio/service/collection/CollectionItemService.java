package ru.otus.collectorio.service.collection;

import ru.otus.collectorio.dto.collection.CollectionItemDto;

import java.util.List;

public interface CollectionItemService {
    List<CollectionItemDto> findAll();

    CollectionItemDto findById(Long id);
}
