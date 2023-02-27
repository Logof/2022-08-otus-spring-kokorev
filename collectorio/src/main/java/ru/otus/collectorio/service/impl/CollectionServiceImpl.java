package ru.otus.collectorio.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.entity.Collection;
import ru.otus.collectorio.mapper.CollectionEntityMapper;
import ru.otus.collectorio.payload.request.collection.CollectionRequest;
import ru.otus.collectorio.repository.CollectionRepository;
import ru.otus.collectorio.service.CollectionService;

import java.util.List;
import java.util.Objects;

@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    private final CollectionEntityMapper mapper;

    public CollectionServiceImpl(CollectionRepository collectionRepository,
                                 CollectionEntityMapper mapper) {
        this.collectionRepository = collectionRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Collection> findAll() {
        return collectionRepository.findAll();
    }

    @Override
    @Transactional
    public Collection save(CollectionRequest collectionRequest) {
        if (Objects.isNull(collectionRequest.getId())) {
            return collectionRepository.setFixedNameById(collectionRequest.getId(), collectionRequest.getName());
        }
        return collectionRepository.save(mapper.toCollection(collectionRequest));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        collectionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectibleItem> findCollectableItemByCollectionId(Long id) {
        return null; //collectionRepository.findById(id).orElseThrow(() -> new DataNotFoundException()).getCollectibleItemList();
    }
}
