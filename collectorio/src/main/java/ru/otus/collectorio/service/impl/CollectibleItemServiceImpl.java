package ru.otus.collectorio.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.mapper.CollectableItemMapper;
import ru.otus.collectorio.payload.request.collectible.CollectibleItemRequest;
import ru.otus.collectorio.payload.response.collectableItem.CollectibleItemResponse;
import ru.otus.collectorio.repository.collectible.CollectibleItemExtRepository;
import ru.otus.collectorio.repository.collectible.CollectibleItemRepository;
import ru.otus.collectorio.service.CollectibleItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectibleItemServiceImpl implements CollectibleItemService {

    private final CollectibleItemRepository collectibleItemRepository;

    private final CollectibleItemExtRepository collectibleItemExtRepository;

    private final CollectableItemMapper mapper;

    public CollectibleItemServiceImpl(CollectibleItemRepository collectibleItemRepository,
                                      CollectibleItemExtRepository collectibleItemExtRepository,
                                      CollectableItemMapper mapper) {
        this.collectibleItemRepository = collectibleItemRepository;
        this.collectibleItemExtRepository = collectibleItemExtRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectibleItemResponse> findAll() {
        return collectibleItemRepository.findAll()
                .stream().map(item -> mapper.toCollectibleItemResponse(item))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CollectibleItemResponse findById(Long id) {
        return mapper.toCollectibleItemResponse(collectibleItemRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException()));
    }

    @Override
    @Transactional
    public CollectibleItem save(CollectibleItemRequest item) {
        return collectibleItemRepository.save(mapper.toCollectibleItem(item));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        collectibleItemRepository.deleteById(id);
    }

}
