package ru.otus.collectorio.service;

import ru.otus.collectorio.entity.Collection;

import java.util.List;

public interface CollectionService {

    List<Collection> findAll();

    Collection save(Collection collection);

    void deleteById(Long id);
}
