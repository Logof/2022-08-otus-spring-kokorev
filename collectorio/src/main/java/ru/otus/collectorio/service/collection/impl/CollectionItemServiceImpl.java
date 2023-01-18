package ru.otus.collectorio.service.collection.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.dto.collection.CollectionItemDto;

import ru.otus.collectorio.mapper.collection.CollectionItemMapper;
import ru.otus.collectorio.repository.collection.CollectionItemRepository;
import ru.otus.collectorio.service.collection.CollectionItemService;

import java.util.List;

@Service
public class CollectionItemServiceImpl implements CollectionItemService {

    private final CollectionItemRepository collectionItemRepository;

    private final CollectionItemMapper collectionItemMapper;

    public CollectionItemServiceImpl(CollectionItemRepository collectionItemRepository,
                                     CollectionItemMapper collectionItemMapper) {
        this.collectionItemRepository = collectionItemRepository;
        this.collectionItemMapper = collectionItemMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectionItemDto> findAll() {
        return collectionItemMapper.toDtos(collectionItemRepository.findAll());
    }

    @Override
    public CollectionItemDto findById(Long id) {
        return collectionItemMapper.toDto(collectionItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found")));
    }
}
