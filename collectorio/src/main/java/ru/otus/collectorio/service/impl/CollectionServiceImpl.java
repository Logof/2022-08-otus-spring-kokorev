package ru.otus.collectorio.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.Collection;
import ru.otus.collectorio.repository.CollectionRepository;
import ru.otus.collectorio.service.CollectionService;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    public CollectionServiceImpl(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Collection> findAll() {
        return collectionRepository.findAll();
    }

    @Override
    @Transactional
    public Collection save(Collection collection) {
        return collectionRepository.save(collection);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        collectionRepository.deleteById(id);
    }
}
