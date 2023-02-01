package ru.otus.collectorio.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.CollectibleItem;
import ru.otus.collectorio.exception.DataNotFoundException;
import ru.otus.collectorio.repository.CollectibleItemRepository;
import ru.otus.collectorio.service.CollectibleItemService;

import java.util.List;

@Service
public class CollectibleItemServiceImpl implements CollectibleItemService {

    private final CollectibleItemRepository collectibleItemRepository;

    public CollectibleItemServiceImpl(CollectibleItemRepository collectibleItemRepository) {
        this.collectibleItemRepository = collectibleItemRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectibleItem> findAll() {
        return collectibleItemRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CollectibleItem findById(Long id) {
        return collectibleItemRepository.findById(id).orElseThrow(() -> new DataNotFoundException());
    }

    @Override
    @Transactional
    public CollectibleItem save(CollectibleItem item) {
        return collectibleItemRepository.save(item);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        collectibleItemRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CollectibleItem> findByCollection(Long id) {
        return collectibleItemRepository.findAllByCollection_Id(id);
    }
}
