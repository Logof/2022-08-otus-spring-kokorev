package ru.otus.collectorio.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.collectorio.entity.CollectibleItem;

import java.util.List;

public interface CollectibleItemService {
    List<CollectibleItem> findAll();

    CollectibleItem findById(Long id);

    CollectibleItem save(CollectibleItem item);

    @Transactional
    void deleteById(Long id);
}
