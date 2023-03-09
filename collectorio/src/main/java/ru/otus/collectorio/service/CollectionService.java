package ru.otus.collectorio.service;

import ru.otus.collectorio.payload.request.collection.CollectionRequest;
import ru.otus.collectorio.payload.response.collection.CollectionResponse;

import java.util.List;

public interface CollectionService {

    List<CollectionResponse> findAll();

    CollectionResponse save(CollectionRequest collection);

    void deleteById(Long id);
}
